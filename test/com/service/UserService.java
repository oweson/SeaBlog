package com.service;

import com.base.BaseClass;
import top.sea521.entity.User;
import top.sea521.mapper.UserMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/9/21 0021 21:01
 */
public class UserService extends BaseClass {
    @Autowired
    UserMapper userMapper;
    @Test
    public void userTest(){
        User user = userMapper.selectByPrimaryKey(1);
        System.out.println("ppxpppppppppp"+user+"ppppppppppppppppppppppppppppppppp");

    }

}
