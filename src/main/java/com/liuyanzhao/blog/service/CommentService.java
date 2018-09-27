package com.liuyanzhao.blog.service;

import com.liuyanzhao.blog.entity.Comment;
import com.liuyanzhao.blog.entity.custom.CommentCustom;
import com.liuyanzhao.blog.entity.custom.CommentListVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by 言曌 on 2017/9/10.
 */

public interface CommentService {
    /**
     * 添加评论*？
     * void insertComment(HttpServletRequest request, Comment comment) throws Exception;
     * <p>
     * /**根据文章id获取评论列表
     */
    List<CommentCustom> listCommentByArticleId(Integer status, Integer articleId);

    /**
     * 根据id获取评论
     */
    CommentCustom getCommentById(Integer id) throws Exception;


    /**
     * 获取所有评论列表
     */
    List<CommentListVo> listCommentByPage(Integer status, Integer pageNow, Integer pageSize) throws Exception;

    /**
     * 获得评论列表
     */
    List<CommentListVo> listCommentVo(Integer status) throws Exception;

    /**
     * 获得评论列表
     */
    List<CommentCustom> listComment(Integer status) throws Exception;

    /**
     * 删除评论
     */
    void deleteComment(Integer id) throws Exception;

    /**
     * 修改评论
     */
    void updateComment(Comment comment) throws Exception;

    /**
     * 统计评论数
     */
    Integer countComment(Integer status) throws Exception;

    /**
     * 获得最近评论
     */
    List<CommentListVo> listRecentComment(Integer limit) throws Exception;


    /**
     * 获得评论的子评论
     */
    List<Comment> listChildComment(Integer id) throws Exception;

    /**
     * by oweson 新增插入评论
     */
    void insertComment(HttpServletRequest request, Comment comment) throws Exception;


}
