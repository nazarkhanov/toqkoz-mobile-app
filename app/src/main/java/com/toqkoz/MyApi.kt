package com.toqkoz
import com.toqkoz.data.AuthRequest
import com.toqkoz.data.NotificationData
import com.toqkoz.data.TokenData
import com.toqkoz.data.TrackerData
import com.toqkoz.data.UserData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface MyApi  {

    @GET("notifications")
    fun getNotifications():Call<List<NotificationData>>

    @GET("trackers")
    fun getTrackers():Call<List<TrackerData>>

    @POST("login")
    fun logIn(
        @Body request: AuthRequest
    ) :Call<TokenData>

    @GET("profile")
    fun authorize(
        @Header("Authorization") token: String
    ): Call<UserData>

}



