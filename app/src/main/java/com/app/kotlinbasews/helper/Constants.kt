package com.app.kotlinbasews.helper

object Constants {

    //private static final String BASE_HOST = String.format("%s", "http://ws-srv-net.in.webmyne.com:80/Applications/eatup/EatUp_Services/api/");//webmyne testing
    private val BASE_HOST = String.format("%s", "http://ws-srv-net.in.webmyne.com:80/Applications/eatup/EatUp_Services/api/")


    /*public static String getBaseHost() {
        return BASE_HOST;
    }*/
    fun getBaseHost(): String {
        return BASE_HOST
    }

}