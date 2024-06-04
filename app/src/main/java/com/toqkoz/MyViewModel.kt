package com.toqkoz

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toqkoz.data.AuthRequest
import com.toqkoz.data.LoginStatus
import com.toqkoz.data.NotificationData
import com.toqkoz.data.TokenData
import com.toqkoz.data.TrackerData
import com.toqkoz.data.UserData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyViewModel(private val prefs: SharedPreferences): ViewModel() {

    var currentUser = UserData()

    val api = Retrofit.Builder()
        .baseUrl("https://api.toqkoz.kz/android/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MyApi::class.java)


    val loginStatus = MutableStateFlow(LoginStatus.WAITING.name)

    val loginValue = MutableStateFlow("")

    val passwordValue = MutableStateFlow("")

    val notificationsList = MutableStateFlow(emptyList<NotificationData>())

    val trackersList = MutableStateFlow(emptyList<TrackerData>())

    val selectedNotification = MutableStateFlow(NotificationData())

    val selectedTracker = MutableStateFlow(TrackerData())

    val isLoading = MutableStateFlow(false)

    init {
        getProfile()
    }

    fun updateNotifications(){
        viewModelScope.launch {
            isLoading.value = true
            uploadNotificationsList()
            kotlinx.coroutines.delay(1000L)
            isLoading.value = false
        }
    }
    fun updateTrackers(){
        viewModelScope.launch {
            isLoading.value = true
            uploadTrackersList()
            kotlinx.coroutines.delay(1000L)
            isLoading.value = false
        }
    }

    fun uploadNotificationsList() {
        viewModelScope.launch {

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

    }
    fun uploadTrackersList() {
        viewModelScope.launch {

            api.getTrackers().enqueue(object : Callback<List<TrackerData>> {

                override fun onResponse(
                    call: Call<List<TrackerData>>,
                    response: Response<List<TrackerData>>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { trackers ->
                            // Update the notificationsList
                            Log.i("RESPONSE_CHECK", "success")

                            trackersList.value = trackers
                        }
                    }
                }

                override fun onFailure(call: Call<List<TrackerData>>, t: Throwable) {
                    t.message?.let { Log.i("RESPONSE_CHECK", "fail") }
                }
            })
        }

    }
    fun signin(authRequest: AuthRequest){
        viewModelScope.launch {
            try {

                api.logIn(authRequest).enqueue(object : Callback<TokenData> {

                    override fun onResponse(call: Call<TokenData>, response: Response<TokenData>) {

                        if (response.isSuccessful){
                            response.body()?.let {
                                Log.i("YES", it.token)
                                prefs.edit().putString("jwt", it.token)
                                    .apply()
                            }
                            Log.i("RESPONSE_CHECK", "RESPONSE")

                            loginStatus.value = LoginStatus.LOGGEDIN.name
                            getProfile()
                        }else{
                            loginStatus.value = LoginStatus.WRONGCREDENTIALS.name
                        }
                    }

                    override fun onFailure(call: Call<TokenData>, t: Throwable) {

                        loginStatus.value = LoginStatus.WRONGCREDENTIALS.name
                        Log.i("RESPONSE_CHECK", "failFAIL")

                    }
                })
            } catch (e:Exception){
                loginStatus.value = LoginStatus.WRONGCREDENTIALS.name
                passwordValue.value = ""
                Log.i("RESPONSE_CHECK", e.message.toString())
            }
        }

    }

    fun signOut(){
        currentUser = UserData()
        prefs.edit().putString("jwt", "")
            .apply()
        loginStatus.value = LoginStatus.LOGGING.name
        passwordValue.value = ""

    }

    fun getProfile(){
        val token = prefs.getString("jwt", "")
        val bearerToken = "Bearer $token"

        if (token != "") {
            api.authorize(bearerToken).enqueue(object : Callback<UserData> {
                override fun onResponse(call: Call<UserData>, response: Response<UserData>) {
                    if (response.isSuccessful){
                        response.body()?.let {
                            currentUser = response.body()!!
                        }
                        loginStatus.value = LoginStatus.LOGGEDIN.name
                    }else{
                        Log.i("KAMA", response.errorBody().toString())
                        loginStatus.value = LoginStatus.LOGGING.name
                    }
                }

                override fun onFailure(call: Call<UserData>, t: Throwable) {
                    Log.i("KAMA", " ")
                }
            })
        }else{
            loginStatus.value = LoginStatus.LOGGING.name
        }


    }

    fun selectNotification(notification: NotificationData){
        selectedNotification.value = notification
    }

    fun selectTracker(tracker: TrackerData){
        selectedTracker.value = tracker
    }

}