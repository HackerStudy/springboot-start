package com.hackerstudy.adminclient.controller.websocket;

import com.hackerstudy.adminclient.common.vo.JSONResult;
import com.hackerstudy.adminclient.websocket.WebSocketServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @class: ForwardNews
 * @description:
 * @author: Administrator
 * @date: 2019-05-23 16:50
 */
@RestController
@RequestMapping("forwardnews")
public class WebSocketController {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketController.class);

    @GetMapping("/push")
    public JSONResult<?> forwardNews(String message) {
        try {
            WebSocketServer.sendToAllMessage(message);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return JSONResult.ok("推送成功", message);
    }
}
