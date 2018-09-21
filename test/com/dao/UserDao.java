package com.dao;

import com.base.BaseClass;
import com.liuyanzhao.blog.entity.User;
import com.liuyanzhao.blog.mapper.UserMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/9/21 0021 20:40
 */
public class UserDao extends BaseClass {
    @Autowired
    UserMapper userMapper;
    @Test
    public void userTest(){
        User user = userMapper.selectByPrimaryKey(1);
        System.out.println(user.toString());

    }
}
