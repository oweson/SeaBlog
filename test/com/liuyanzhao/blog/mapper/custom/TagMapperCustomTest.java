package com.liuyanzhao.blog.mapper.custom;

import com.base.BaseClass;
import com.liuyanzhao.blog.entity.custom.ArticleCustom;
import com.liuyanzhao.blog.entity.custom.TagCustom;
import javafx.scene.control.TableFocusModel;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/9/27 0027 20:18
 */
public class TagMapperCustomTest extends BaseClass {
    @Autowired
    TagMapperCustom tagMapperCustom;

    @Test
    public void countTag() throws Exception {
        Integer integer = tagMapperCustom.countTag(1);
        System.out.println("============================" + integer);
    }

    @Test
    public void listTag() throws Exception {
        /**两个状态为2 的被查处*/
        List<TagCustom> tagCustoms = tagMapperCustom.listTag(2);
        System.out.println("=========================================" + tagCustoms);
    }

    @Test
    public void listArticleWithTagByPage () throws  Exception {
        Integer a=1;
        Integer b=2;
        List<ArticleCustom> articleCustoms = tagMapperCustom.listArticleWithTagByPage(1,1,1,2);
        System.out.println("========================================================"+articleCustoms.toString());

    }

    @Test
    public void getTagByName() {
    }
}