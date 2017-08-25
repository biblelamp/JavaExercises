package com.javarush.task.task09.task0902;

/* 
И снова StackTrace
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        method1();
    }

    public static String method1() {
        method2();
        //System.out.println(Thread.currentThread().getStackTrace()[2].getMethodName());
        return Thread.currentThread().getStackTrace()[2].getMethodName();
    }

    public static String method2() {
        method3();
        //System.out.println(Thread.currentThread().getStackTrace()[2].getMethodName());
        return Thread.currentThread().getStackTrace()[2].getMethodName();
    }

    public static String method3() {
        method4();
        //System.out.println(Thread.currentThread().getStackTrace()[2].getMethodName());
        return Thread.currentThread().getStackTrace()[2].getMethodName();
    }

    public static String method4() {
        method5();
        //System.out.println(Thread.currentThread().getStackTrace()[2].getMethodName());
        return Thread.currentThread().getStackTrace()[2].getMethodName();
    }

    public static String method5() {
        //System.out.println(Thread.currentThread().getStackTrace()[2].getMethodName());
        return Thread.currentThread().getStackTrace()[2].getMethodName();
    }
}