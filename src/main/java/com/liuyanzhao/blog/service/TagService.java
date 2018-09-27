package com.liuyanzhao.blog.service;

import com.liuyanzhao.blog.entity.Tag;
import com.liuyanzhao.blog.entity.custom.ArticleListVo;
import com.liuyanzhao.blog.entity.custom.TagCustom;

import java.util.List;

/**
 * Created by 言曌 on 2017/9/2.
 */
public interface TagService {

    /**
     * 获得标签总数
     */
    Integer countTag(Integer status) throws Exception;

    /**
     * 获得标签列表
     */
    List<TagCustom> listTag(Integer status) throws Exception;

    /**
     * 获得含有该标签的文章列表
     */
    List<ArticleListVo> getArticleListByPage(Integer status, Integer pageNow, Integer pageSize, Integer tagId) throws Exception;

    /**
     * 根据id获得标签信息
     */
    TagCustom getTagById(Integer id) throws Exception;

    /**
     * 添加标签
     */
    void insertTag(Tag tag) throws Exception;

    /**
     * 修改标签
     */
    void updateTag(Tag tag) throws Exception;

    /**
     * 删除标签
     */
    void deleteTag(Integer id) throws Exception;

    /**
     * 根据标签名获取标签
     */
    Tag getTagByName(String name) throws Exception;

}
