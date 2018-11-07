package top.sea521.controller.Admin;
/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/6/13
 */

import top.sea521.entity.Article;
import top.sea521.entity.custom.*;
import top.sea521.service.ArticleService;
import top.sea521.service.CategoryService;
import top.sea521.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/admin/article")
public class BackArticleController {
    @Autowired
    private ArticleService articleService;


    @Autowired
    private TagService tagService;

    @Autowired
    private CategoryService categoryService;

    /**
     * 1为后台文章列表显示
     */
    @RequestMapping(value = "")
    public ModelAndView index() throws Exception {
        ModelAndView modelAndView = new ModelAndView();

        /**分页显示已发布文章*/
        Integer pageSize = 8;
        List<ArticleListVo> publishedArticleListVoList = articleService.listArticleByPage(1, null, pageSize);
        modelAndView.addObject("publishedArticleListVoList", publishedArticleListVoList);

        /**不分页显示 草稿文章*/
        List<ArticleListVo> draftArticleList = articleService.listArticle(0);
        modelAndView.addObject("draftArticleList", draftArticleList);
        modelAndView.setViewName("Admin/Article/index");
        return modelAndView;
    }

    /**
     * 2 文章分页显示
     * pageNow是什么开始的页码
     */
    //todo 有bug!!!待修复......l
    @RequestMapping("/p/{pageNow}")
    public @ResponseBody
    ModelAndView ArticleListByPageView(@PathVariable("pageNow") Integer pageNow) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        //分页显示已发布文章
        Integer pageSize = 20;
        List<ArticleListVo> publishedArticleListVoList = articleService.listArticleByPage(1, pageNow, pageSize);
        modelAndView.addObject("publishedArticleListVoList", publishedArticleListVoList);

        //不分页显示 草稿文章
        List<ArticleListVo> draftArticleList = articleService.listArticle(0);
        modelAndView.addObject("draftArticleList", draftArticleList);
        modelAndView.setViewName("Admin/Article/index");
        return modelAndView;
    }

    /**
     * 3 后台添加文章页面显示
     */
    @RequestMapping(value = "/insert")
    public ModelAndView insertArticleView() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        /**发表文章，分类和标签的信息的回显示*/

        List<CategoryCustom> categoryCustomList = categoryService.listCategory(1);
        List<TagCustom> tagCustomList = tagService.listTag(1);

        modelAndView.addObject("categoryCustomList", categoryCustomList);
        modelAndView.addObject("tagCustomList", tagCustomList);

        modelAndView.setViewName("Admin/Article/insert");
        return modelAndView;
    }

    /**
     * 4 后台添加文章提交操作
     */
    @RequestMapping(value = "/insertSubmit", method = RequestMethod.POST)
    public String insertArticleSubmit(Article article) throws Exception {
        /**这些字段是后台设置，不是前台传入的*/
        /**可能到了草稿箱，并未持久到数据库*/

        article.setArticlePostTime(new Date());
        article.setArticleUpdateTime(new Date());
        article.setArticleIsComment(1);
        /**为了鼓励用户，默认浏览量2100个！！！*/
        article.setArticleViewCount(2100);
        article.setArticleLikeCount(0);
        article.setArticleCommentCount(0);
        /**状态是显示的*/
        article.setArticleStatus(1);
        article.setArticleOrder(1);

        articleService.insertArticle(article);

        return "redirect:/admin/article";
    }

    /**
     * 4后台添加文章提交操作和上一个有什么区别？？？
     */
    @RequestMapping(value = "/insertDraftSubmit", method = RequestMethod.POST)
    /**draft:草稿，草案*/
    public String insertArticleDraftSubmit(Article article) throws Exception {

        article.setArticlePostTime(new Date());
        article.setArticleUpdateTime(new Date());
        article.setArticleIsComment(1);
        article.setArticleViewCount(0);
        article.setArticleLikeCount(0);
        article.setArticleCommentCount(0);
        /**状态有区别！！！发布是1*/
        article.setArticleStatus(0);
        article.setArticleOrder(1);

        articleService.insertArticle(article);

        return "redirect:/admin/article";
    }


    /**
     * 5搜索实现
     */
    @RequestMapping("/search")
    @ResponseBody
    public ModelAndView SearchPageView(HttpServletRequest request, Model model) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        //设置每页显示的数量
        int pageSize = 10;
        /**query参数？？？*/
        String query = request.getParameter("query");
        List<ArticleSearchVo> articleSearchVoList = articleService.listSearchResultByPage(1, request, model, null, pageSize, query);
        modelAndView.addObject("articleSearchVoList", articleSearchVoList);
        modelAndView.setViewName("Admin/Article/search");
        return modelAndView;
    }

    /**
     * 6搜索分页实现
     */
    @RequestMapping("/p/{pageNow}/search")
    @ResponseBody
    public ModelAndView SearchPageByPageView(HttpServletRequest request, Model model, @PathVariable("pageNow") Integer pageNow) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        //设置每页显示的数量
        int pageSize = 20;
        String query = request.getParameter("query");
        List<ArticleSearchVo> articleSearchVoList = articleService.listSearchResultByPage(1, request, model, pageNow, pageSize, query);
        modelAndView.addObject("articleSearchVoList", articleSearchVoList);
        modelAndView.setViewName("/Admin/Article/search");
        return modelAndView;
    }


    /**
     * 6删除文章
     */
    @RequestMapping(value = "/delete/{id}")
    public void deleteArticle(@PathVariable("id") Integer id) throws Exception {
        //调用service批量删除
        articleService.deleteArticle(id);
    }

    /**
     * 7批量删除文章
     */
    @RequestMapping(value = "/deleteBatch")
    public void deleteArticles(HttpServletRequest request) throws Exception {
        String str = request.getParameter("ids");
        String[] arr = str.split(",");
        Integer[] ids = new Integer[arr.length];
        for (int i = 0; i < arr.length; i++) {
            ids[i] = Integer.valueOf(arr[i]);
        }
        /**调用service批量删除,数组封装id的集合*/
        articleService.deleteArticleBatch(ids);

    }

    /**
     * 8 编辑文章页面显示,根据文章的id进行一次回显示
     */
    @RequestMapping(value = "/edit/{id}")
    public ModelAndView editArticleView(@PathVariable("id") Integer id) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
/** 1 查询文章*/
        ArticleCustom articleCustom = articleService.getArticleById(null, id);
        modelAndView.addObject("articleCustom", articleCustom);
/** 2 查询文章对应的分类*/
        List<CategoryCustom> categoryCustomList = categoryService.listCategory(1);
        modelAndView.addObject("categoryCustomList", categoryCustomList);
/** 3 查询文章对应的标签*/
        List<TagCustom> tagCustomList = tagService.listTag(1);
        modelAndView.addObject("tagCustomList", tagCustomList);


        modelAndView.setViewName("Admin/Article/edit");
        return modelAndView;
    }


    /**
     * 9 编辑文章提交,根据id区更新文章
     */
    @RequestMapping(value = "/editSubmit", method = RequestMethod.POST)
    public String editArticleSubmit(ArticleCustom articleCustom) throws Exception {
        /**拿到文章的id*/
        Integer id = articleCustom.getArticleId();
        /**设置更新时间*/
        articleCustom.setArticleUpdateTime(new Date());
        articleService.updateArticle(id, articleCustom);
        return "redirect:/admin/article";
    }


}



