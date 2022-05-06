package com.an.test.personalitytestapp.tools;

public class Util {

    public static boolean isEmpty(String str){
        if(str == null || "".equalsIgnoreCase(str.trim())){
            return true;
        }
        return false;
    }
}
