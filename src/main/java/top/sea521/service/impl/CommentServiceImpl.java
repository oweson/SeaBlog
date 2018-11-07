package top.sea521.service.impl;

import top.sea521.entity.Comment;
import top.sea521.entity.custom.ArticleCustom;
import top.sea521.entity.custom.CommentListVo;
import top.sea521.mapper.ArticleMapper;
import top.sea521.mapper.CommentMapper;
import top.sea521.mapper.custom.ArticleMapperCustom;
import top.sea521.mapper.custom.CommentMapperCustom;
import top.sea521.entity.custom.CommentCustom;
import top.sea521.service.CommentService;
import top.sea521.util.others.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import top.sea521.util.Functions;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapperCustom commentMapperCustom;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private ArticleMapperCustom articleMapperCustom;

    /**
     * 1 插入评论
     */
    @Override
    public void insertComment(HttpServletRequest request, Comment comment) throws Exception {
        String ip = Functions.getIpAddr(request);
        /**得到访问者的ip一块入库，让不法之徒无法逃匿*/
        comment.setCommentIp(ip);
        commentMapper.insertSelective(comment);
    }

    /**
     * 2 某一偏文章的评论集合
     */
    @Override
    public List<CommentCustom> listCommentByArticleId(Integer status, Integer articleId) {
        List<CommentCustom> commentCustomList = commentMapperCustom.listCommentByArticleId(status, articleId);
        return commentCustomList;
    }

    /**
     * 3 通过id查询评论的集合
     */
    //todo 蒙逼
    @Override
    public CommentCustom getCommentById(Integer id) throws Exception {
        CommentCustom commentCustom = new CommentCustom();
        Comment comment = commentMapper.selectByPrimaryKey(id);
        BeanUtils.copyProperties(comment, commentCustom);
        return commentCustom;
    }

    /**
     * 4 分页评论集合
     */
    @Override
    public List<CommentListVo> listCommentByPage(Integer status, Integer pageNow, Integer pageSize) throws Exception {
        List<CommentListVo> commentListVoList = new ArrayList<CommentListVo>();

        List<CommentCustom> commentCustomList = new ArrayList<CommentCustom>();


        Page page ;
        int totalCount = commentMapperCustom.countComment(status);
        if (pageNow != null) {
            page = new Page(totalCount, pageNow, pageSize);
            commentCustomList = commentMapperCustom.listCommentByPage(status, page.getStartPos(), pageSize);
        } else {
            page = new Page(totalCount, 1, pageSize);
            commentCustomList = commentMapperCustom.listCommentByPage(status, page.getStartPos(), pageSize);
        }


        for (int i = 0; i < commentCustomList.size(); i++) {
            CommentListVo commentListVo = new CommentListVo();
            //获得文章信息
            Integer articleId = commentCustomList.get(i).getCommentArticleId();
            ArticleCustom articleCustom = articleMapperCustom.getArticleById(status, articleId);
            commentListVo.setArticleCustom(articleCustom);

            //评论信息
            CommentCustom commentCustom = commentCustomList.get(i);
            //评论者Gravatar头像
            String avatar = Functions.getGravatar(commentCustom.getCommentAuthorEmail());
            commentCustom.setCommentAuthorAvatar(avatar);
            commentListVo.setCommentCustom(commentCustomList.get(i));

            commentListVoList.add(commentListVo);
        }

        if (commentListVoList.size() > 0) {
            //4、将Page信息存储在第一个元素中
            commentListVoList.get(0).setPage(page);
        }
        return commentListVoList;
    }

    /**
     * 4 批量集合不分页
     */
    @Override
    public List<CommentListVo> listCommentVo(Integer status) throws Exception {
        List<CommentListVo> commentListVoList = new ArrayList<CommentListVo>();

        List<CommentCustom> commentCustomList = commentMapperCustom.listComment(status);

        for (int i = 0; i < commentCustomList.size(); i++) {
            CommentListVo commentListVo = new CommentListVo();
            //获得文章信息
            Integer articleId = commentCustomList.get(i).getCommentArticleId();
            ArticleCustom articleCustom = articleMapperCustom.getArticleById(status, articleId);
            commentListVo.setArticleCustom(articleCustom);

            //评论信息
            CommentCustom commentCustom = commentCustomList.get(i);
            //评论者Gravatar头像
            String avatar = Functions.getGravatar(commentCustom.getCommentAuthorEmail());
            commentCustom.setCommentAuthorAvatar(avatar);
            commentListVo.setCommentCustom(commentCustomList.get(i));

            commentListVoList.add(commentListVo);
        }

        return commentListVoList;
    }

    /**
     * 5 所有的评论
     */
    @Override
    public List<CommentCustom> listComment(Integer status) throws Exception {
        List<CommentCustom> commentCustomList = commentMapperCustom.listComment(status);
        return commentCustomList;
    }

    /**
     * 6 删除评论
     */
    @Override
    public void deleteComment(Integer id) throws Exception {
        commentMapper.deleteByPrimaryKey(id);
    }

    /**
     * 7 更新评论
     */
    @Override
    public void updateComment(Comment comment) throws Exception {
        commentMapper.updateByPrimaryKeySelective(comment);
    }

    /**
     * 8 统计评论的数量
     */
    @Override
    public Integer countComment(Integer status) throws Exception {
        Integer commentCount = commentMapperCustom.countComment(status);
        return commentCount;
    }

    /**
     * 9 列举最近的评论集合
     */
    @Override
    public List<CommentListVo> listRecentComment(Integer limit) throws Exception {
        List<CommentListVo> recentCommentList = new ArrayList<CommentListVo>();
        List<CommentCustom> commentCustomList = commentMapperCustom.listRecentComment(limit);
        for (int i = 0; i < commentCustomList.size(); i++) {
            CommentListVo commentListVo = new CommentListVo();
            //给每个评论用户添加头像
            String avatar = Functions.getGravatar(commentCustomList.get(i).getCommentAuthorEmail());
            CommentCustom commentCustom = commentCustomList.get(i);
            commentCustom.setCommentAuthorAvatar(avatar);
            commentListVo.setCommentCustom(commentCustom);
            //找到评论对应的文章信息
            ArticleCustom articleCustom = articleMapperCustom.getArticleById(1, commentCustom.getCommentArticleId());
            commentListVo.setArticleCustom(articleCustom);

            recentCommentList.add(commentListVo);
        }

        return recentCommentList;
    }

    /**
     * 10 列出所有的子评论
     */
    @Override
    public List<Comment> listChildComment(Integer id) throws Exception {
        List<Comment> childCommentList = commentMapperCustom.listChildComment(id);
        return childCommentList;
    }

}
