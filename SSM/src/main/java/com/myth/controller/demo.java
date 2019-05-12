package com.myth.controller;

import com.myth.entity.SysUser;
import com.myth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
@CrossOrigin("*")
public class demo {

    @Autowired
    UserService userService;

    @GetMapping()
    public String index() {
        return "index";
    }


    @PostMapping("/test")
    @ResponseBody
    public SysUser test(@RequestParam String id) {
        SysUser user1 = null;
        return userService.getUserById(1L);
    }
}