package com.todayworker.springboot.utils;

import java.util.UUID;

public class UuidUtils {

    public static String generateNoDashUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
