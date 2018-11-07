package top.sea521.enums;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/11/7 0007 22:46
 */
public enum OrderStatesEnum {
    FIRSE(1,"MAX"),SECOND(0,"MIDDLE");
    private Integer code;
    private String desc;

    OrderStatesEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
