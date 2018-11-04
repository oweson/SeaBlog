package top.sea521.mapper.custom;

import top.sea521.entity.Comment;
import top.sea521.entity.custom.CommentCustom;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface CommentMapperCustom {

    /**
     * 1 根据文章id获取评论列表
     */
    List<CommentCustom> listCommentByArticleId(@Param(value = "status") Integer status, @Param(value = "id") Integer id);

    /**
     * 2 获得评论列表分页
     */
    List<CommentCustom> listCommentByPage(@Param(value = "status") Integer status, @Param(value = "startPos") Integer startPos, @Param(value = "pageSize") Integer pageSize) throws Exception;

    /**
     * 3 获得评论列表
     */

    List<CommentCustom> listComment(@Param(value = "status") Integer status) throws Exception;

    /**
     * 4 统计评论数
     */
    Integer countComment(@Param(value = "status") Integer status) throws Exception;

    /**
     * 5 获得最近评论
     */
    List<CommentCustom> listRecentComment(@Param(value = "limit") Integer limit) throws Exception;

    /**
     * 6 获得评论的子评论
     */
    List<Comment> listChildComment(@Param(value = "id") Integer id) throws Exception;

}
