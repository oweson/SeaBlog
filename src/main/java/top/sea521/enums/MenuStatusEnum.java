package top.sea521.enums;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/11/7 0007 22:46
 */
public enum MenuStatusEnum {
    /**
     * 菜单的枚举信息；
     * 不提供set fun 防止外部修改枚举的信息；
     */

    FIRSE(1, "可以用"), SECOND(0, "不可以用");
    private Integer code;
    private String desc;

    MenuStatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

   /* public void setCode(Integer code) {
        this.code = code;
    }*/

    public String getDesc() {
        return desc;
    }


}
