package top.sea521.mapper.custom;

import com.base.BaseClass;
import top.sea521.entity.custom.CategoryCustom;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/9/27 0027 21:14
 */
public class CategoryMapperCustomTest extends BaseClass {
    @Autowired
    CategoryMapperCustom categoryMapperCustom;

    @Test
    public void countCategory() throws Exception {
        Integer integer = categoryMapperCustom.countCategory(1);
        System.out.println("===================================================="+integer);
    }

    @Test
    public void listCategory() throws Exception {
        List<CategoryCustom> categoryCustoms = categoryMapperCustom.listCategory(1);
        for (CategoryCustom categoryCustom : categoryCustoms) {
            System.out.println("==============================="+categoryCustom);

        }

    }

    @Test
    public void getCategoryById() throws Exception {
        CategoryCustom categoryById = categoryMapperCustom.getCategoryById(1,1);
        System.out.println("============================================"+categoryById);


    }

    @Test
    public void listArticleWithCategoryByPage() {
    }

    @Test
    public void deleteCategory() {
    }

    @Test
    public void findChildCategory() {
    }

    @Test
    public void getCategoryByName() {
    }
}