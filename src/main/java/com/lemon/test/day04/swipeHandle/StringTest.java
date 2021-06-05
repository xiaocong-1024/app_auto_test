package com.lemon.test.day04.swipeHandle;

/**
 * @author xiaocong
 * @date 2021/5/24 0024 - 14:03
 */
public class StringTest {
    public static void main(String[] args){
        //[72,534][1008,1470]
        String markPoint = "[72,534][1008,1470]";
        String[] splits = markPoint.split("]",2);
        String pointItem = splits[0];
        int x0 = Integer.valueOf(pointItem.substring(1).split(",")[0]);
        int y0 = Integer.valueOf(pointItem.substring(1).split(",")[1]);
        System.out.println(x0);
        System.out.println(y0);
    }
}
