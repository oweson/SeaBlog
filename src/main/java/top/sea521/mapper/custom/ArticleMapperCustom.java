package top.sea521.mapper.custom;

import top.sea521.entity.custom.ArticleCustom;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ArticleMapperCustom {
    /**
     * 1 获取文章总数
     */
    public Integer countArticle(@Param(value = "status") Integer status) throws Exception;

    /**
     * 2 获得留言总数
     */
    public Integer countArticleComment(@Param(value = "status") Integer status) throws Exception;

    /**
     * 3 获得浏览量总数
     */
    public Integer countArticleView(@Param(value = "status") Integer status) throws Exception;

    /**
     * 4获得所有文章(文章归档)
     */
    public List<ArticleCustom> listArticle(@Param(value = "status") Integer status) throws Exception;

    /**
     * 5根据id查询用户信息
     */
    public ArticleCustom getArticleById(@Param(value = "status") Integer status, @Param(value = "id") Integer id) throws Exception;

    /**
     * 6分页操作
     */
    public List<ArticleCustom> listArticleByPage(@Param(value = "status") Integer status, @Param(value = "startPos") Integer startPos, @Param(value = "pageSize") Integer pageSize) throws Exception;

    /**
     * 7文章结果查询结果的数量
     */
    public Integer getSearchResultCount(@Param(value = "status") Integer status, @Param(value = "query") String query) throws Exception;

    /**
     * 8查询文章分页操作
     */
    public List<ArticleCustom> listSearchResultByPage(@Param(value = "status") Integer status, @Param(value = "query") String query, @Param(value = "startPos") Integer startPos, @Param(value = "pageSize") Integer pageSize) throws Exception;

    /**
     * 9获得同分类的文章(相关文章)
     */
    public List<ArticleCustom> listArticleWithSameCategory(
            @Param(value = "status") Integer status,
            @Param(value = "parentCategory") Integer parentCategory
            , @Param(value = "childCategory") Integer childCategory
            , @Param(value = "limit") Integer limit
    ) throws Exception;

    /**
     * 10获得访问最多的文章(猜你喜欢)
     */
    public List<ArticleCustom> listArticleByViewCount(@Param(value = "status")
                                                              Integer status, @Param(value = "limit") Integer limit) throws Exception;

    /**
     * 11 获得上一篇文章
     */
    public ArticleCustom getAfterArticle(@Param(value = "status")
                                                 Integer status, @Param(value = "id") Integer id) throws Exception;

    /**
     * 12获得下一篇文章
     */
    public ArticleCustom getPreArticle(@Param(value = "status") Integer status, @Param(value = "id") Integer id) throws Exception;

    /**
     * 13获得随机文章
     */
    public List<ArticleCustom> listRandomArticle(@Param(value = "status") Integer status, @Param(value = "limit") Integer limit) throws Exception;

    /**
     * 14热评文章
     */
    public List<ArticleCustom> listArticleByCommentCount(@Param(value = "status") Integer status, @Param(value = "limit") Integer limit) throws Exception;

    /**
     * 15获得该分类的文章数
     */
    public Integer countArticleByCategory(@Param(value = "status") Integer status, @Param(value = "id") Integer id) throws Exception;

    /**
     * 16获得该标的文章数
     */
    public Integer countArticleByTag(@Param(value = "status") Integer status, @Param(value = "id") Integer id) throws Exception;

    /**
     * 17更新文章的评论数
     */
    public void updateCommentCount(@Param(value = "articleId") Integer articleId) throws Exception;

    /**
     * 18获得最后更新的记录
     */
    public ArticleCustom getLastUpdateArticle() throws Exception;
}



