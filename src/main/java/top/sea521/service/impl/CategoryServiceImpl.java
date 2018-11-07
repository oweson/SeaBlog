package top.sea521.service.impl;

import com.google.common.collect.Lists;
import top.sea521.mapper.CategoryMapper;
import top.sea521.mapper.custom.ArticleMapperCustom;
import top.sea521.mapper.custom.CategoryMapperCustom;
import top.sea521.entity.Category;
import top.sea521.entity.custom.ArticleCustom;
import top.sea521.entity.custom.ArticleListVo;
import top.sea521.entity.custom.CategoryCustom;
import top.sea521.service.CategoryService;
import top.sea521.util.others.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapperCustom categoryMapperCustom;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ArticleMapperCustom articleMapperCustom;

    /**
     * 1 统计分类
     */
    @Override
    public Integer countCategory(Integer status) throws Exception {
        Integer categoryCount = categoryMapperCustom.countCategory(status);
        return categoryCount;
    }

    /**
     * 2 查询所有的分类
     */
    @Override
    public List<CategoryCustom> listCategory(Integer status) throws Exception {
        List<CategoryCustom> categoryCustomList = categoryMapperCustom.listCategory(status);
        for (int i = 0; i < categoryCustomList.size(); i++) {
            /**得到分类的id*/
            Integer cateId = categoryCustomList.get(i).getCategoryId();
            /**统计分类下面的文章的总数*/
            Integer count = articleMapperCustom.countArticleByCategory(status, cateId);
            /**设置分类下的总数*/
            categoryCustomList.get(i).setArticleCount(count);
        }

        return categoryCustomList;
    }

    /**
     * 3 某个分类下的文章的集合
     */
    //todo 有bug urlnull会400c错误
    @Override
    public List<ArticleListVo> listArticleWithCategoryByPage(Integer status, Integer pageNow,
                                                             Integer pageSize, Integer cateId) throws Exception {
        List<ArticleListVo> articleListVoList = new ArrayList<ArticleListVo>();
        List<ArticleCustom> articleCustomList = new ArrayList<ArticleCustom>();

        /**获得分类的信息*/
        Category category = categoryMapper.selectByPrimaryKey(cateId);
        //如果没有这个分类，返回null
        if (category == null) {
            return null;
        }

        /**分页显示*/
        Page page = null;
        /**统计这个分类下的文章的总数，为分页做准备！*/
        int totalCount = articleMapperCustom.countArticleByCategory(status, cateId);

        if (pageNow != null) {
            //todo 设置分页的默认值
            page = new Page(totalCount, pageNow, pageSize);
            articleCustomList = categoryMapperCustom.listArticleWithCategoryByPage(status, cateId, page.getStartPos(), page.getPageSize());
        } else {
            page = new Page(totalCount, 1, pageSize);
            articleCustomList = categoryMapperCustom.listArticleWithCategoryByPage(status, cateId, page.getStartPos(), page.getPageSize());

        }
        for (int i = 0; i < articleCustomList.size(); i++) {
            ArticleListVo articleListVo = new ArticleListVo();

            /**1、将文章信息装入 articleListVo*/
            ArticleCustom articleCustom = articleCustomList.get(i);
            articleListVo.setArticleCustom(articleCustom);


            //2、将分类信息装到 articleListVoList 中
            List<CategoryCustom> categoryCustomList = new ArrayList<CategoryCustom>();
            Integer parentCategoryId = articleCustomList.get(i).getArticleParentCategoryId();
            Integer childCategoryId = articleCustomList.get(i).getArticleChildCategoryId();
            CategoryCustom categoryCustom = categoryMapperCustom.getCategoryById(status, parentCategoryId);
            CategoryCustom categoryCustom2 = categoryMapperCustom.getCategoryById(status, childCategoryId);
            if (categoryCustom != null) {
                categoryCustomList.add(categoryCustom);
            }
            if (categoryCustom2 != null) {
                categoryCustomList.add(categoryCustom2);
            }
            articleListVo.setCategoryCustomList(categoryCustomList);

            articleListVoList.add(articleListVo);
        }
        //如果该分类还没有文章
        if (totalCount != 0) {
            //2、将Page信息存储在第一个元素中
            articleListVoList.get(0).setPage(page);
        }
        return articleListVoList;
    }

    /**
     * 4 查看某一个分类信息
     */
    @Override
    public CategoryCustom getCategory(Integer status, Integer id) throws Exception {
        CategoryCustom categoryCustom = categoryMapperCustom.getCategoryById(status, id);
        return categoryCustom;
    }

    /**
     * 6 删除某一个分类
     */
    @Override
    public void deleteCategory(Integer id) throws Exception {
        categoryMapperCustom.deleteCategory(id);
    }

    /**
     * 7 查询一个分类信息通过id
     */
    @Override
    public CategoryCustom getCategoryById(Integer status, Integer id) throws Exception {
        CategoryCustom categoryCustom = categoryMapperCustom.getCategoryById(status, id);
        return categoryCustom;
    }

    /**
     * 8 插入分类
     */
    @Override
    public void insertCategory(Category category) throws Exception {
        categoryMapper.insertSelective(category);
    }

    /**
     * 9 更新分类
     */
    @Override
    public void updateCategory(Category category) throws Exception {
        categoryMapper.updateByPrimaryKeySelective(category);
    }

    /**
     * 10 通过名字查找分类
     */
    @Override
    public Category getCategoryByName(String name) throws Exception {
        Category category = categoryMapperCustom.getCategoryByName(name);
        return category;
    }


}
