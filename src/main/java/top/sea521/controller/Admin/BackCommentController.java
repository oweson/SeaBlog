package top.sea521.controller.Admin;
/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/6/13
 */


import top.sea521.entity.Article;
import top.sea521.entity.Comment;
import top.sea521.entity.custom.CommentCustom;
import top.sea521.entity.custom.CommentListVo;
import top.sea521.enums.PageStatusEnum;
import top.sea521.service.ArticleService;
import top.sea521.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/admin/comment")
public class BackCommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ArticleService articleService;

    /**
     * 1 后台评论列表显示
     */
    @RequestMapping(value = "")
    public ModelAndView commentListView() throws Exception {
        ModelAndView modelandview = new ModelAndView("Admin/Comment/index");
        /**正常评论显示,正常和不长长的区别是状态0表示是正常的，1 是不可以用的*/
        //todo 分页第二页没有效果
        List<CommentListVo> commentListVoList = commentService.listCommentByPage(null, null, PageStatusEnum.MIN.getCode());
        modelandview.addObject("commentListVoList", commentListVoList);
        /**待审核评论不分页显示*/
        List<CommentListVo> hiddenCommentListVoList = commentService.listCommentVo(0);
        modelandview.addObject("hiddenCommentListVoList", hiddenCommentListVoList);
        return modelandview;

    }

    /**
     * 2 后台评论分页显示,有bug!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     */
    @RequestMapping("p/{pageNow}")
    //适合RESTful
    public @ResponseBody
    ModelAndView commentListByPageView(@PathVariable("pageNow") Integer pageNow) throws Exception {
        ModelAndView modelandview = new ModelAndView("Admin/Comment/index");
        /**全部评论分页显示,bug..........................................................*/
        Integer pageSize = 20;
        List<CommentListVo> commentListVoList = commentService.listCommentByPage(null, pageNow, pageSize);
        modelandview.addObject("commentListVoList", commentListVoList);
        /**待审核评论不分页显示*/
        List<CommentListVo> hiddenCommentListVoList = commentService.listCommentVo(0);
        modelandview.addObject("hiddenCommentListVoList", hiddenCommentListVoList);
        return modelandview;

    }

    /**
     * 2 添加评论
     */
    //todo
    @RequestMapping(value = "/insert", method = {RequestMethod.POST})
    @ResponseBody
    public void insertComment(HttpServletRequest request, Comment comment) throws Exception {
        //添加评论
        comment.setCommentCreateTime(new Date());
        commentService.insertComment(request, comment);
        /**更新文章的评论数,提交评论评论总数加一，评论对应文章的id,
         * 根据文章的id查询文章*/
        Article article = articleService.getArticleById(null, comment.getCommentArticleId());
        articleService.updateCommentCount(article.getArticleId());
    }

    /**
     * 3 删除评论
     */
    @RequestMapping(value = "/delete/{id}")
    public void deleteComment(@PathVariable("id") Integer id) throws Exception {
        Comment comment = commentService.getCommentById(id);
        //删除评论
        commentService.deleteComment(id);
        /**删除其子评论???迭代器？？？*/
        List<Comment> childCommentList = commentService.listChildComment(id);
        for (int i = 0; i < childCommentList.size(); i++) {
            //todo 循环删除不好，在mybatis批量的删除
            commentService.deleteComment(childCommentList.get(i).getCommentId());
        }
        /**更新文章的评论数*/
        Article article = articleService.getArticleById(null, comment.getCommentArticleId());
        articleService.updateCommentCount(article.getArticleId());
    }

    /**
     * 4 编辑评论页面显示
     */
    @RequestMapping(value = "/edit/{id}")
    public ModelAndView editCommentView(@PathVariable("id") Integer id) throws Exception {
        ModelAndView modelAndView = new ModelAndView("Admin/Comment/edit");
        CommentCustom commentCustom = commentService.getCommentById(id);
        modelAndView.addObject("commentCustom", commentCustom);
        return modelAndView;
    }


    /**
     * 5 编辑评论提交
     */
    @RequestMapping(value = "/editSubmit", method = RequestMethod.POST)
    public String editCommentSubmit(Comment comment) throws Exception {
        commentService.updateComment(comment);
        Integer id = comment.getCommentId();
        /**如果是屏蔽评论*/
        if (comment.getCommentStatus() == 0) {
            /**屏蔽其子评论*/
            List<Comment> childCommentList = commentService.listChildComment(id);
            for (int i = 0; i < childCommentList.size(); i++) {
                Comment childComment = childCommentList.get(i);
                /**设置频闭的状态*/
                childComment.setCommentStatus(0);
                commentService.updateComment(childComment);
            }
        }
        /**如果是批准评论*/
        else {
            /**批准其子评论*/
            List<Comment> childCommentList = commentService.listChildComment(id);
            for (int i = 0; i < childCommentList.size(); i++) {
                Comment childComment = childCommentList.get(i);
                childComment.setCommentStatus(1);
                commentService.updateComment(childComment);
            }
        }
        return "redirect:/admin/comment";
    }

    /**
     * 7 批准评论
     */
    @RequestMapping(value = "/approve/{id}")
    public void approveComment(@PathVariable("id") Integer id) throws Exception {
        Comment comment = commentService.getCommentById(id);
        /**批准评论,把状态进行修改1是批准的，0是不可以用的*/
        comment.setCommentStatus(1);
        commentService.updateComment(comment);
        //批准其子评论
        List<Comment> childCommentList = commentService.listChildComment(id);
        for (int i = 0; i < childCommentList.size(); i++) {
            Comment childComment = childCommentList.get(i);
            childComment.setCommentStatus(1);
            commentService.updateComment(childComment);
        }

        //更新文章的评论数
        Article article = articleService.getArticleById(null, comment.getCommentArticleId());
        articleService.updateCommentCount(article.getArticleId());
    }

    /**
     * 8 屏蔽评论
     */
    @RequestMapping(value = "/hide/{id}")
    public void hideComment(@PathVariable("id") Integer id) throws Exception {
        Comment comment = commentService.getCommentById(id);
        /**屏蔽评论,把状态修改为0就是频闭*/
        comment.setCommentStatus(0);
        commentService.updateComment(comment);
        //屏蔽其子评论
        List<Comment> childCommentList = commentService.listChildComment(id);
        for (int i = 0; i < childCommentList.size(); i++) {
            Comment childComment = childCommentList.get(i);
            childComment.setCommentStatus(0);
            commentService.updateComment(childComment);
        }
        //更新文章的评论数
        Article article = articleService.getArticleById(null, comment.getCommentArticleId());
        articleService.updateCommentCount(article.getArticleId());
    }

    //回复评论页面显示
    @RequestMapping(value = "/reply/{id}")
    public ModelAndView replyCommentView(@PathVariable("id") Integer id) throws Exception {
        ModelAndView modelAndView = new ModelAndView();

        CommentCustom commentCustom = commentService.getCommentById(id);
        modelAndView.addObject("commentCustom", commentCustom);

        modelAndView.setViewName("Admin/Comment/reply");
        return modelAndView;
    }

    /**
     * 8回复评论提交
     */
    @RequestMapping(value = "/replySubmit", method = RequestMethod.POST)
    public String replyCommentSubmit(HttpServletRequest request, Comment comment) throws Exception {
        /**文章评论数+1，提交后文章的评论数加一*/
        Article article = articleService.getArticleById(null, comment.getCommentArticleId());
        article.setArticleCommentCount(article.getArticleCommentCount() + 1);
        articleService.updateArticle(article.getArticleId(), article);
        //添加评论
        comment.setCommentCreateTime(new Date());
        comment.setCommentStatus(1);
        commentService.insertComment(request, comment);
        return "redirect:/admin/comment";
    }

}
