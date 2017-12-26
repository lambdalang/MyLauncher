package com.tufer.mylauncher;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by TUFER on 2017/12/7 0007.
 */

public class Test {
    public static void main(String[] args) throws ParseException {
        Date date = new Date();
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        String s1 = sdf.format(date);  //2015-02-09  format()才是格式化
        Date t1 = null;
        t1 = sdf.parse(s1);//Mon Feb 09 00:00:00 CST 2015  parse()的话是转成Date类型
        System.out.println("s1:"+s1);
        System.out.println("t1:"+t1);
    }
}
