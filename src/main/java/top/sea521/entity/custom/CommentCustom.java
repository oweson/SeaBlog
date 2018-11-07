package top.sea521.entity.custom;

import top.sea521.entity.Comment;


public class CommentCustom extends Comment {
    /**评论者的头像*/
    private String commentAuthorAvatar;

    public String getCommentAuthorAvatar() {
        return commentAuthorAvatar;
    }

    public void setCommentAuthorAvatar(String commentAuthorAvatar) {
        this.commentAuthorAvatar = commentAuthorAvatar;
    }
}
