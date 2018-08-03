package com.app.kotlinbasews.api.responsepojos

data class User(
        val LoginId: Int, // 30259
        var FirstName: String, // Prashant
        val LastName: String, // Prajapati
        val Gender: String, // M
        val ContactNo: String, // 9825612321
        val EmailAddress: String, // prashant@webmynesystems.com
        val ProfilePicUrl: String, // http://ws-srv-net.in.webmyne.com/Applications/eatup/EatUp_Admin/Uploads/UserImages/6790a61f-424a-4230-b7e8-c371e8c9e142.png
        val IsEmailVerified: Boolean, // true
        var CartId: Int, // 10208
        val CartQuantity: Int, // 3
        val cartRestaurentId: Int, // 51
        val NetAmount: Double, // 186.87
        val RestaurantName: String, // Woody John Pizza
        val CurrentOrders: Int, // 35
        val OrderProcessAlerts: Boolean, // true
        val OffersAlerts: Boolean, // true
        val ReadNtfCounter: Int // 4
)