package com.ohaotian.ssoclientrest.controller;

import com.ohaotian.ssoclientrest.dao.User;
import com.ohaotian.ssoclientrest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created: luQi
 * Date:2018-4-27 14:57
 */
@RestController
@RequestMapping("/test")
public class WebController {

    @Autowired
    private UserService userService;


    @GetMapping("/index")
    public Object test(@RequestParam(value = "username", required = false) String username) {
        User user = userService.login(username);
        return user;
    }

}
