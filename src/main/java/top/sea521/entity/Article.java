package top.sea521.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Article {
    private Integer articleId;

    private Integer articleUserId;

    private String articleTitle;

    private Integer articleParentCategoryId;

    private Integer articleChildCategoryId;

    private String articleTagIds;

    private Integer articleViewCount;

    private Integer articleCommentCount;

    private Integer articleLikeCount;

    private Date articlePostTime;

    private Date articleUpdateTime;

    private Integer articleIsComment;

    private Integer articleStatus;

    private Integer articleOrder;

    private String articleContent;


}