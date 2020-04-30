package com.example.lib;

public class MyStrings {

    public static void main(String[] args) {
        String s="vijay nailwal";
        System.out.println(s.substring(5));//nailwal
        System.out.println(s.substring(0,5));//vijay
        System.out.println(s.toLowerCase());//vijay nailwal
        System.out.println(s.toUpperCase());//VIJAY NAILWAL
        System.out.println(s.startsWith("vi"));//true
        System.out.println(s.charAt(1));//i
        System.out.println(s.length());//13

        System.out.println(s.replace("vijay","gaurav"));










    }

}
