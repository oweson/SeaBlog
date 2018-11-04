package top.sea521.mapper.custom;


import top.sea521.entity.Tag;
import top.sea521.entity.custom.ArticleCustom;
import top.sea521.entity.custom.TagCustom;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface TagMapperCustom {
    /**
     * 1  根据传入的标签的状态tag_state获得标签总数
     */
    Integer countTag(@Param(value = "status") Integer status) throws Exception;

    /**
     * 2 根据传入的标签的状态获得标签列表
     */
    List<TagCustom> listTag(@Param(value = "status") Integer status) throws Exception;

    /**
     * 3 获得带有该标签的文章列表
     */
    List<ArticleCustom> listArticleWithTagByPage(@Param(value = "status") Integer status, @Param(value = "tagId") Integer tagId, @Param(value = "startPos") Integer startPos, @Param(value = "pageSize") Integer pageSize) throws Exception;

    /**
     * 4 根据标签名获取标签
     */
    Tag getTagByName(String name) throws Exception;

}
