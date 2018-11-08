package top.sea521.enums;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/11/7 0007 22:46
 */
public enum PageStatusEnum {
    MAX(20, "20size"), MIDDLE(0, "15size"), MIN(10, "10size");
    private Integer code;
    private String desc;

    PageStatusEnum(Integer code, String desc) {
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
