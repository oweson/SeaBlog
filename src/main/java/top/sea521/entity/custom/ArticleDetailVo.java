package top.sea521.entity.custom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 用于封装文章正文详细信息，包括文章内容信息，作者信息，分类和标签信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDetailVo {
    /**
     * 1 文章相关信息
     */
    private ArticleCustom articleCustom;

    /**
     * 2 文章的作者相关信息
     */
    private UserCustom userCustom;

    /**
     * 3 文章的分类相关信息
     */
    private List<CategoryCustom> categoryCustomList;

    /**
     * 4 文章的标签相关信息
     */
    private List<TagCustom> tagCustomList;

    /**
     * 5 评论信息
     */
    private List<CommentCustom> commentCustomList;


}
