package com.stackroute.controller;

import com.stackroute.domain.User;
import com.stackroute.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secure")
public class securecontroller {

   private UserService userService;

    @Autowired
    public securecontroller(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/user/users")
    public String loginSuccess() {
        return "Login Successful!";
    }

    @RequestMapping(value = "/user/username", method = RequestMethod.POST)
    public User findByUsername(@RequestBody String username)
    {
        System.out.println("secure");
        System.out.println(userService.findByUserName(username));
        return userService.findByUserName(username);
    }

    @RequestMapping(value = "/user/update", method = RequestMethod.POST)
    public User updateUser(@RequestBody User user) {
        System.out.println(user);
        return userService.save(user);
    }
}
