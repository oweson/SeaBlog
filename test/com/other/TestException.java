package com.other;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/9/27 0027 16:20
 */
public class TestException {
    /**
     * @param args
     */
    public static void main(String[] args) {
        int a[] = {0,1,2,3,4};
        int sum=0;
        try
        {
            for(int i=0;i<5;i++)
            {
                sum+=a[i];
            }
            System.out.println("sum="+sum);
        }
        catch(java.lang.ArrayIndexOutOfBoundsException e)
        {
            System.out.println("数组下标越界");
        }
        finally
        {
            System.out.println("程序结束");
        }
    }

}
