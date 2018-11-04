package top.sea521.service;

import top.sea521.entity.Tag;
import top.sea521.entity.custom.ArticleListVo;
import top.sea521.entity.custom.TagCustom;

import java.util.List;


public interface TagService {

    /**
     * 1 获得标签总数
     */
    Integer countTag(Integer status) throws Exception;

    /**
     * 2 获得标签列表
     */
    List<TagCustom> listTag(Integer status) throws Exception;

    /**
     * 3 获得含有该标签的文章列表
     */
    List<ArticleListVo> getArticleListByPage(Integer status, Integer pageNow, Integer pageSize, Integer tagId) throws Exception;

    /**
     * 4 根据id获得标签信息
     */
    TagCustom getTagById(Integer id) throws Exception;

    /**
     * 5 添加标签
     */
    void insertTag(Tag tag) throws Exception;

    /**
     * 6 修改标签
     */
    void updateTag(Tag tag) throws Exception;

    /**
     * 7  删除标签
     */
    void deleteTag(Integer id) throws Exception;

    /**
     * 8 根据标签名获取标签
     */
    Tag getTagByName(String name) throws Exception;

}
