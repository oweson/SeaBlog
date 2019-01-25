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
public enum OrderStatesEnum {
    /**
     * 1 订单枚举类信息
     */
    FIRSE(1, "MAX"), SECOND(0, "MIDDLE");
    private Integer code;
    private String desc;


}
