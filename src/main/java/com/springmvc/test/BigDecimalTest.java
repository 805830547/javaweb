package com.springmvc.test;

import common.calculate.Arith;

/*
1 public BigDecimal add(BigDecimal value);                        //加法
2 public BigDecimal subtract(BigDecimal value);                   //减法
3 public BigDecimal multiply(BigDecimal value);                   //乘法
4 public BigDecimal divide(BigDecimal value);                     //除法
*/
public class BigDecimalTest {

    public static void main(String[] args) {
        System.out.println("======================Before=========================");
        System.out.println(0.06 + 0.01);
        System.out.println(1.0 - 0.42);
        System.out.println(4.015 * 100);
        System.out.println(303.1 / 1000);
        System.out.println("======================After=========================");
        System.out.println(Arith.add(0.06, 0.01));
        System.out.println(Arith.sub(1.0, 0.42));
        System.out.println(Arith.mul(4.015, 100));
        System.out.println(Arith.div(303.1, 1000));
    }

}
