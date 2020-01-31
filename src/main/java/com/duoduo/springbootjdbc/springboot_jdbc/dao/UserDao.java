package com.duoduo.springbootjdbc.springboot_jdbc.dao;

import com.duoduo.springbootjdbc.springboot_jdbc.pojo.User;

import java.util.List;

public interface UserDao {
    //插入方法
    int insert(User record);

    //查找其中的用户数
    List<User> selectUsers();
}
