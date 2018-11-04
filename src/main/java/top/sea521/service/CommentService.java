package top.sea521.service;

import top.sea521.entity.Comment;
import top.sea521.entity.custom.CommentCustom;
import top.sea521.entity.custom.CommentListVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public interface CommentService {
    /**
     * void insertComment(HttpServletRequest request, Comment comment) throws Exception;
     * <p>
     * 1 根据文章id获取评论列表
     */
    List<CommentCustom> listCommentByArticleId(Integer status, Integer articleId);

    /**
     * 2 根据id获取评论
     */
    CommentCustom getCommentById(Integer id) throws Exception;


    /**
     * 3 获取所有评论列表
     */
    List<CommentListVo> listCommentByPage(Integer status, Integer pageNow, Integer pageSize) throws Exception;

    /**
     * 4 获得评论列表
     */
    List<CommentListVo> listCommentVo(Integer status) throws Exception;

    /**
     * 5 获得评论列表
     */
    List<CommentCustom> listComment(Integer status) throws Exception;

    /**
     * 6  删除评论
     */
    void deleteComment(Integer id) throws Exception;

    /**
     * 7 修改评论
     */
    void updateComment(Comment comment) throws Exception;

    /**
     * 8 统计评论数
     */
    Integer countComment(Integer status) throws Exception;

    /**
     * 9 获得最近评论
     */
    List<CommentListVo> listRecentComment(Integer limit) throws Exception;


    /**
     * 10 获得评论的子评论
     */
    List<Comment> listChildComment(Integer id) throws Exception;

    /**
     * by oweson 11 新增插入评论
     */
    void insertComment(HttpServletRequest request, Comment comment) throws Exception;


}
