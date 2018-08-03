package com.app.kotlinbasews.helper

object AppConstants {
    private val BASE_HOST = String.format("%s", "http://ws-srv-net.in.webmyne.com:80/Applications/eatup/EatUp_Services/api/")
    fun getBaseHost(): String {
        return BASE_HOST
    }

    val LOGIN_URL: String = "User/Login"

}