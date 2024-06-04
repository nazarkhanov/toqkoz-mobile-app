package com.toqkoz

import android.util.Log
import androidx.lifecycle.ViewModel
import com.toqkoz.data.NotificationData
import com.toqkoz.data.TrackerData
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyViewModel: ViewModel() {

    val notificationsList = MutableStateFlow(emptyList<NotificationData>())

    val selectedNotification = MutableStateFlow(NotificationData())

    val selectedTracker = MutableStateFlow(TrackerData())

    val isLoading = MutableStateFlow(false)

    fun updateNotifications(){
        isLoading.value = true
        uploadNotificationsList()
        isLoading.value = false
    }

    fun uploadNotificationsList() {
        val api = Retrofit.Builder()
            .baseUrl("http://194.15.46.253:8000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MyApi::class.java)

        api.getNotifications().enqueue(object : Callback<List<NotificationData>> {
            override fun onResponse(
                call: Call<List<NotificationData>>,
                response: Response<List<NotificationData>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { notifications ->
                        // Update the notificationsList
                        Log.i("RESPONSE_CHECK", "success")

                        notificationsList.value = notifications
                    }
                }
            }

            override fun onFailure(call: Call<List<NotificationData>>, t: Throwable) {
                t.message?.let { Log.i("RESPONSE_CHECK", "fail") }
            }
        })
    }

    fun selectNotification(notification: NotificationData){
        selectedNotification.value = notification
    }

    fun selectTracker(tracker: TrackerData){
        selectedTracker.value = tracker
    }

}