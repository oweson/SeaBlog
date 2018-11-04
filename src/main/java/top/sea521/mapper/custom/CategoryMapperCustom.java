package top.sea521.mapper.custom;


import top.sea521.entity.Category;
import top.sea521.entity.custom.ArticleCustom;
import top.sea521.entity.custom.CategoryCustom;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;




@Repository
public interface CategoryMapperCustom {
    /**
     * 1 查询分类总数
     */
    Integer countCategory(@Param(value = "status") Integer status) throws Exception;

    /**
     * 2 获得分类列表
     */
    List<CategoryCustom> listCategory(@Param(value = "status") Integer status) throws Exception;

    /**
     * 3 根据分类id和分类的状态status获得分类信息
     */
    CategoryCustom getCategoryById(@Param(value = "status") Integer status, @Param(value = "id") Integer id) throws Exception;

    /**
     * 4 获得含有该分类的文章列表
     */
    List<ArticleCustom> listArticleWithCategoryByPage(
            @Param(value = "status") Integer status,
            @Param(value = "cateId") Integer cateId
            , @Param(value = "startPos") Integer startPos
            , @Param(value = "pageSize") Integer pageSize
    ) throws Exception;

    /**
     * 5 删除分类
     */
    void deleteCategory(Integer id) throws Exception;

    /**
     * 6 根据父分类找子分类
     */
    List<CategoryCustom> findChildCategory(@Param(value = "status") Integer status, @Param(value = "id") Integer id) throws Exception;

    /**
     * 7 根据标签名获取标签
     */
    Category getCategoryByName(String name) throws Exception;
    /** 8 根据分类的id区查询分类放入信息*/

    CategoryCustom getCategoryById(int i);
}



