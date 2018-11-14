package top.sea521.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private Integer commentId;

    private Integer commentPid;

    private String commentPname;

    private Integer commentArticleId;

    private String commentAuthorName;

    private String commentAuthorEmail;

    private String commentAuthorUrl;

    private String commentContent;

    private String commentAgent;

    private String commentIp;

    private Date commentCreateTime;

    private Integer commentRole;

    private Integer commentStatus;


}