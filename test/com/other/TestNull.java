package com.other;

import org.apache.commons.lang3.StringUtils;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/9/22 0022 10:28
 */
public class TestNull {
    public static void main(String[] args) {
        Integer.valueOf(2);
        String s="  ";
        String s1 = StringUtils.trimToNull(s);
        final boolean notEmpty = StringUtils.isNotEmpty(s);
        boolean notEmpty1 = StringUtils.isNotEmpty(s1);
        System.out.println(notEmpty1+"aaaaaaaaaaaaaaaaaaaaaaaa");

        System.out.println(s1);
        Integer a=null;
        Integer b=100;
        if(a==null){

        }
    }
}
