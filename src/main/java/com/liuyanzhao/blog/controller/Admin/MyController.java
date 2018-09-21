package com.liuyanzhao.blog.controller.Admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/9/19 0019 20:36
 */
@Controller
public class MyController {
    @RequestMapping("/my")
    @ResponseBody
    public Object test(){
int i=1/0;
        return "hello,oweson";
    }
}
