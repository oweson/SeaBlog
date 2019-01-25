package top.sea521.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/11/7 0007 22:46
 */
@Getter
@AllArgsConstructor
public enum PageStatusEnum {
    /**
     * 1 页面枚举类信息
     */
    MAX(20, "20size"), MIDDLE(0, "15size"), MIN(10, "10size");
    private Integer code;
    private String desc;


}
