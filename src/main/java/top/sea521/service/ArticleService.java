package top.sea521.service;

import top.sea521.entity.Article;
import top.sea521.entity.custom.ArticleCustom;
import top.sea521.entity.custom.ArticleDetailVo;
import top.sea521.entity.custom.ArticleListVo;
import top.sea521.entity.custom.ArticleSearchVo;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public interface ArticleService {
    /**
     * 1 获取文章总数
     */
    Integer countArticle(Integer status) throws Exception;

    /**
     * 2 获取评论总数
     */
    Integer countArticleComment(Integer status) throws Exception;

    /**
     * 3 获得浏览量总数
     */
    Integer countArticleView(Integer status) throws Exception;

    /**
     * 4 获得所有文章不分页
     */
    List<ArticleListVo> listArticle(Integer status) throws Exception;

    /**
     * 5 根据id获得文章
     */
    ArticleCustom getArticleById(Integer status, Integer id) throws Exception;

    /**
     * 6 修改文章信息
     */
    void updateArticle(Integer id, Article article) throws Exception;

    /**
     * 7 批量删除文章
     */
    void deleteArticleBatch(Integer[] ids) throws Exception;

    /**
     * 8 删除文章
     */
    void deleteArticle(Integer id) throws Exception;

    /**
     * 9  分页显示()
     */
    List<ArticleListVo> listArticleByPage(Integer status, Integer pageNow, Integer pageSize) throws Exception;

    /**
     * 10 文章详情页面显示
     */
    ArticleDetailVo getArticleDetailById(Integer id) throws Exception;

    /**
     * 11 文章查询分页显示
     */
    List<ArticleSearchVo> listSearchResultByPage(Integer status, HttpServletRequest request, Model model, Integer pageNow, Integer pageSize, String s) throws Exception;

    /**
     * 12 获得相关文章
     */
    List<ArticleCustom> listArticleWithSameCategory(Integer status, Integer parentCategoryId, Integer childCategoryId, Integer limit) throws Exception;

    /**
     * 13 获取访问量较多的文章
     */
    List<ArticleCustom> listArticleByViewCount(Integer status, Integer limit) throws Exception;

    /**
     * 14 获得上一篇文章
     */
    ArticleCustom getAfterArticle(Integer status, Integer id) throws Exception;

    /**
     * 14 获得下一篇文章
     */
    ArticleCustom getPreArticle(Integer status, Integer id) throws Exception;

    /**
     * 15  获得随机文章
     */
    List<ArticleCustom> listRandomArticle(Integer status, Integer limit) throws Exception;

    /**
     * 17 获得评论数较多的文章
     */
    List<ArticleCustom> listArticleByCommentCount(Integer status, Integer limit) throws Exception;

    /**
     * 18 添加文章
     */
    void insertArticle(Article article) throws Exception;

    /**
     * 19 统计某个分类下的文章的总数
     */

    Integer countArticleWithCategory(Integer status, Integer id) throws Exception;

    /**
     * * 20 获得某个标签的文章数
     */
    Integer countArticleWithTag(Integer status, Integer id) throws Exception;

    /**
     * 21 更新文章的评论数
     */
    void updateCommentCount(Integer articleId) throws Exception;

    /**
     * 22 获得最后更新记录
     */
    ArticleCustom getLastUpdateArticle() throws Exception;
}
