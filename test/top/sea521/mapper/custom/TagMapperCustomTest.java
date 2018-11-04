package top.sea521.mapper.custom;

import com.base.BaseClass;
import top.sea521.entity.custom.ArticleCustom;
import top.sea521.entity.custom.TagCustom;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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

        List<ArticleCustom> articleCustoms = tagMapperCustom.listArticleWithTagByPage(1,1,1,2);
        System.out.println("========================================================"+articleCustoms.toString());

    }

    @Test
    public void getTagByName() {
    }
}