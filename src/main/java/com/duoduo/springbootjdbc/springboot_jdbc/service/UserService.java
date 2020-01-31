package com.duoduo.springbootjdbc.springboot_jdbc.service;

import com.duoduo.springbootjdbc.springboot_jdbc.pojo.User;
import com.github.pagehelper.PageInfo;

public interface UserService {

    //插入我们想要插入的用户信息
    int addUser(User user);

    //分页插件 查找所有的用户
    PageInfo<User> findAllUser(int pageNum,int pageSize);

}
