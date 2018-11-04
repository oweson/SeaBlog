package top.sea521.service;


import top.sea521.entity.Category;
import top.sea521.entity.custom.ArticleListVo;
import top.sea521.entity.custom.CategoryCustom;


import java.util.List;


public interface CategoryService {
    /**
     * 1 获得分类总数
     */
    Integer countCategory(Integer status) throws Exception;

    /**
     * 2 获得分类列表
     */
    List<CategoryCustom> listCategory(Integer status) throws Exception;

    /**
     * 3 获得带有该分类的文章列表
     */
    List<ArticleListVo> listArticleWithCategoryByPage(Integer status, Integer pageNow, Integer pageSize, Integer cateId) throws Exception;

    /**
     * 4 获得某个分类信息
     */
    CategoryCustom getCategory(Integer status, Integer id) throws Exception;

    /**
     * 5  删除分类
     */
    void deleteCategory(Integer id) throws Exception;

    /**
     * 6 根据id查询分类信息
     */
    CategoryCustom getCategoryById(Integer status, Integer id) throws Exception;

    /**
     * 7 添加分类
     */
    void insertCategory(Category category) throws Exception;

    /**
     * 8 更新分类
     */
    void updateCategory(Category category) throws Exception;

    /**
     * 9 根据分类名获取分类
     */
    Category getCategoryByName(String name) throws Exception;


}
