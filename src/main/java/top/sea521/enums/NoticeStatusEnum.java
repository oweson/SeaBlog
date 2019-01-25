package top.sea521.enums;

import lombok.Getter;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/11/7 0007 22:46
 */
@Getter
public enum NoticeStatusEnum {
    /**
     * 公告枚举类信息
     */
    FIRSE(1, "可以用"), SECOND(0, "不可以用");
    private Integer code;
    private String desc;

    NoticeStatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }




}
