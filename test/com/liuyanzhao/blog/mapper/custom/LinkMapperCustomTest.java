package com.liuyanzhao.blog.mapper.custom;

import com.base.BaseClass;
import com.liuyanzhao.blog.entity.custom.LinkCustom;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/9/27 0027 20:51
 */
public class LinkMapperCustomTest extends BaseClass {
    @Autowired
    LinkMapperCustom linkMapperCustom;

    @Test
    public void countLink() throws Exception {
        Integer integer = linkMapperCustom.countLink(1);
        Integer integer1 = linkMapperCustom.countLink(2);
        System.out.println("=============================================="+integer+integer1);
    }

    @Test
    public void listLink() throws Exception {
        List<LinkCustom> linkCustoms = linkMapperCustom.listLink(1);
        System.out.println("========================================================"+linkCustoms);
    }
}