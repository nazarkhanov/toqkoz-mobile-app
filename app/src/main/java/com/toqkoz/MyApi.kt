package com.toqkoz
import com.toqkoz.data.NotificationData
import retrofit2.Call
import retrofit2.http.GET

interface MyApi  {

    @GET("notifications")
    fun getNotifications():Call<List<NotificationData>>
}



