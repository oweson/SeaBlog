package com.dao;

import com.base.BaseClass;
import top.sea521.entity.User;
import top.sea521.mapper.UserMapper;
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
