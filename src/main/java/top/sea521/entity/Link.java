package top.sea521.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Link {
    private Integer linkId;

    private String linkUrl;

    private String linkName;

    private String linkImage;

    private String linkDescription;

    private String linkOwnerNickname;

    private String linkOwnerContact;

    private Date linkUpdateTime;

    private Date linkCreateTime;

    private Integer linkOrder;

    private Integer linkStatus;


}