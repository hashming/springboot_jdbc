package com.duoduo.springbootjdbc.springboot_jdbc.service;

import com.duoduo.springbootjdbc.springboot_jdbc.dao.UserDao;
import com.duoduo.springbootjdbc.springboot_jdbc.pojo.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "userService")
public class UserServiceImpl implements UserService {//实现接口

    @Autowired
    private UserDao userDao;//这里爆红但是不需要进行理会

    @Override
    public int addUser(User user) {
        return userDao.insert(user);//插入用户信息
    }

    @Override
    public PageInfo<User> findAllUser(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);//开始的页数，每一页的现实的数据的条数
        List<User> userDomains = userDao.selectUsers();
        PageInfo result = new PageInfo(userDomains);
        return result;
    }
}
