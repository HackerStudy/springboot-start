package com.hackerstudy.adminclient.websocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hackerstudy.adminclient.pojo.User;
import com.hackerstudy.adminclient.service.IMService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @class: websocket
 * @descrsidtion: websocket的实现类
 * @author: Administrator
 * @date: 2019-05-23 16:08
 */
@ServerEndpoint(value = "/websocket/{userId}")
@Component
public class WebSocketServer {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketServer.class);

    //记录当前连接数
    private static int onlineCount = 0;

    //concurrent包的线程安全Map，用来存放每个客户端对应的WebSocketServer对象。
    private static Map<Integer, WebSocketServer> webSocketServerMap = new ConcurrentHashMap<Integer, WebSocketServer>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    //用户id
    private int userId;

    //用户信息
    private User user;

    //即时通讯的服务类
    private static IMService iMService;

    @Autowired
    public void setIMService(IMService iMService) {
        WebSocketServer.iMService = iMService;
    }

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(@PathParam("userId") int userId, Session session) {
        this.session = session;
        this.userId = userId;
        user = iMService.getUserInfo(userId);
        webSocketServerMap.put(userId, this);     //加入set中
        addOnlineCount();           //在线数加1
        logger.info(user.getName() + " 上线:,当前在线人数为" + getOnlineCount());
        try {
            Map<String, Object> messageMap = new HashMap<>();
            messageMap.put("messageType", "message");
            messageMap.put("message", "欢迎来到该聊天室");
            String message = JSON.toJSONString(messageMap);
            sendMessage(message);
            List<User> onlineUsers = new ArrayList<>();
            for (Map.Entry<Integer, WebSocketServer> entry : webSocketServerMap.entrySet()) {
                onlineUsers.add(entry.getValue().user);
            }
            Map<String, Object> messageToAllMap = new HashMap<>();
            messageToAllMap.put("messageType", "online");
            messageToAllMap.put("message", user.getName() + "上线");
            messageToAllMap.put("users", onlineUsers);
            String messageToAll = JSON.toJSONString(messageToAllMap);
            sendToAllMessage(messageToAll);
        } catch (IOException e) {
            logger.error("websocket IO异常");
        }
    }

    /**
     * 断开连接调用的方法
     */
    @OnClose
    public void onClose() {
        subOnlineCount();
        webSocketServerMap.remove(this.userId);
        List<User> onlineUsers = new ArrayList<>();
        for (Map.Entry<Integer, WebSocketServer> entry : webSocketServerMap.entrySet()) {
            onlineUsers.add(entry.getValue().user);
        }
        Map<String, Object> messageToAllMap = new HashMap<>();
        messageToAllMap.put("messageType", "offline");
        messageToAllMap.put("message", user.getName() + "下线");
        messageToAllMap.put("users", onlineUsers);
        String messageToAll = JSON.toJSONString(messageToAllMap);
        try {
            sendToAllMessage(messageToAll);
        } catch (IOException e) {
            logger.error(user.getName() + " 下线异常", e.getMessage());
        }
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        logger.info("收到来自窗口的信息:" + message);
        //群发消息
        try {
            JSONObject jsonObject = JSON.parseObject(message);
            if (jsonObject.getInteger("to") == null || jsonObject.getInteger("to") == -1) {
                Map<String, Object> messageToAllMap = new HashMap<>();
                messageToAllMap.put("messageType", "clientMessage");
                messageToAllMap.put("message", user.getName() + ": " + jsonObject.getString("message"));
                String messageToAll = JSON.toJSONString(messageToAllMap);
                sendToAllMessage(messageToAll);
            } else {
                Map<String, Object> messageToMap = new HashMap<>();
                messageToMap.put("messageType", "clientMessage");
                messageToMap.put("message", user.getName() + ": " + jsonObject.getString("message"));
                String messageTo = JSON.toJSONString(messageToMap);
                sendMessageTo(messageTo, jsonObject.getInteger("to"));
                sendMessage(messageTo);
            }
        } catch (IOException e) {
            logger.error("群发消息异常", e.getMessage());
        }
    }

    /**
     * 发生错误时的处理
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        logger.error("发生错误");
        error.printStackTrace();
    }

    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    /**
     * 将消息转发给指定的人
     *
     * @param message
     * @param userId
     * @throws IOException
     */
    public void sendMessageTo(String message, int userId) throws IOException {
        webSocketServerMap.get(userId).session.getBasicRemote().sendText(message);
    }

    /**
     * 服务器群发自定义消息
     */
    public static void sendToAllMessage(String message) throws IOException {
        logger.info("推送消息到窗口，推送内容:" + message);
        for (Map.Entry<Integer, WebSocketServer> entry : webSocketServerMap.entrySet()) {
            try {
                entry.getValue().sendMessage(message);
            } catch (IOException e) {
                continue;
            }
        }
    }

    /**
     * 获取连接数
     *
     * @return
     */
    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    /**
     * 增加连接数
     */
    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    /**
     * 减少连接数
     */
    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }
}
