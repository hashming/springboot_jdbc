package com.duoduo.springbootjdbc.springboot_jdbc.controller;

import com.duoduo.springbootjdbc.springboot_jdbc.pojo.User;
import com.duoduo.springbootjdbc.springboot_jdbc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired//自动配置
    private UserService userService;

    @PostMapping("/add")
    public int addUser(User user){
        return userService.addUser(user);
    }

    /*@GetMapping("/all")
    public Object findAllUser(@RequestParam(name = "pageNum",required = true,defaultValue = "1")int pageNum,@RequestParam(name = "pageSize",required = true,defaultValue = "10")int pageSize){
        return userService.findAllUser(pageNum, pageSize);
    }*/

    @PostMapping("/all")
    public Object findAllUser(int pageNum,int pageSize){
        return userService.findAllUser(pageNum, pageSize);
    }
}
