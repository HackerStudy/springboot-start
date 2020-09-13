package com.hackerstudy.adminclient.controller.thymeleaf;

import com.hackerstudy.adminclient.pojo.Resource;
import com.hackerstudy.adminclient.pojo.User;
import com.hackerstudy.adminclient.service.IMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @class: ThymeleafController
 * @description:
 * @author: yangpeng03614
 * @date: 2019-04-09 15:31
 */
@Controller
@RequestMapping("/th")
@PropertySource("classpath:resource.properties")
//@ConfigurationProperties(prefix = "com.hackerstudy.adminclient")
public class ThymeleafController {

    @Autowired
    Resource resource;

    @Autowired
    IMService imService;

    @Value("${com.hackerstudy.adminclient.websocketIP}")
    String websocketIP;

    @RequestMapping("/thymeleafIndex")
    public String index() {
        return "thymeleaf/thymeleaf_index";
    }

    @RequestMapping("/resource")
    public String resource(ModelMap map) {
        map.addAttribute("resource", resource);
        return "thymeleaf/resource/resource";
    }

    @RequestMapping("/user")
    public String user(ModelMap map) {
        User user = new User();
        user.setId(1);
        user.setName("superadmin");
        user.setPassword("123456");
        user.setAge(24);
        user.setBirthday(new Date());
        user.setDesc("<font color='green'><b>hello world</b></font>");
        List<User> userList = new ArrayList<>();
        userList.add(new User(2, "李四", "123456", 18, new Date(), null));
        userList.add(new User(3, "王五", "123456", 24, new Date(), null));
        userList.add(new User(4, "赵六", "123456", 26, new Date(), null));
        map.addAttribute("u", user);
        map.addAttribute("userList", userList);
        return "thymeleaf/user/user";
    }

    @PostMapping("postform")
    public String postform(User u) {

        System.out.println("姓名：" + u.getName());
        System.out.println("年龄：" + u.getAge());

        return "redirect:/th/user";
    }

    /**
     * @author: yangpeng1
     * @description: 访问聊天室的客户端
     * @param map
     * @param userId
     * @return String
     */
    @GetMapping("/client/{userId}")
    public String client(ModelMap map, @PathVariable Integer userId) {
        List<User> chatRoomUser = imService.getChatRoomUser();
        map.put("userId", userId);
        map.put("websocketIP", websocketIP);
        map.put("ChatRoomUser", chatRoomUser);
        return "thymeleaf/websocket/client";
    }
}
