package com.liuyanzhao.blog.mapper.custom;

import com.base.BaseClass;
import com.liuyanzhao.blog.entity.User;
import com.liuyanzhao.blog.entity.custom.UserCustom;
import junit.runner.BaseTestRunner;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/9/27 0027 20:00
 */
public class UserMapperCustomTest extends BaseClass {
    @Autowired
    UserMapperCustom userMapperCustom;

    @Test
    public void listUser() throws Exception {
        List<UserCustom> userCustoms = userMapperCustom.listUser();
        for (UserCustom userCustom : userCustoms) {
            System.out.println(userCustom);

        }
    }

    @Test
    public void getUserByNameOrEmail() {
    }

    @Test
    public void getUserByName() throws Exception {
        User ppx = userMapperCustom.getUserByName("oweson");
        System.out.println(ppx+"=====================================================================");

    }

    @Test
    public void getUserByEmail() {
    }

    @Test
    public void countArticleByUser() throws Exception {
        Integer integer = userMapperCustom.countArticleByUser(1);
        System.out.println("================================================="+integer);
    }
}