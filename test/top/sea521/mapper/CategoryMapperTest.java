package top.sea521.mapper;

import com.base.BaseClass;
import top.sea521.entity.Category;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/11/2 0002 20:04
 */
public class CategoryMapperTest extends BaseClass {
    @Autowired
    private CategoryMapper categoryMapper;

    @Test
    public void deleteByPrimaryKey() {
    }

    @Test
    public void insert() {
        Category category = new Category();
        category.setCategoryName("dugutianxia");
        category.setCategoryDescription("aa");
        int insert = categoryMapper.insert(category);
        Assert.assertEquals(1, insert);
    }

    @Test
    public void insertSelective() {
    }

    @Test
    public void selectByPrimaryKey() {
    }

    @Test
    public void updateByPrimaryKeySelective() {
    }

    @Test
    public void updateByPrimaryKey() {
    }
}