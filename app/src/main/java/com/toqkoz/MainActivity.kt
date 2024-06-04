package com.toqkoz

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.messaging.FirebaseMessaging
import com.toqkoz.data.LoginStatus
import com.toqkoz.ui.screens.AuthScreen
import com.toqkoz.ui.screens.HomeScreen
import com.toqkoz.ui.theme.ToqkozTheme

enum class MainScreens {
    AUTH,
    HOME,
}

class MainActivity : ComponentActivity() {
    lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestNotificationPermission()
        requestFirebaseToken()
        val viewModel= MyViewModel(application.getSharedPreferences("prefs", MODE_PRIVATE))
        viewModel.uploadNotificationsList() // loading the data to viewmodel
        viewModel.uploadTrackersList()
        viewModel.getProfile()


        setContent {
            ToqkozTheme {
                navController = rememberNavController()
                var startDest = MainScreens.AUTH.name
                val loginStatus by viewModel.loginStatus.collectAsState()
                if (loginStatus == LoginStatus.LOGGING.name){
                    startDest = MainScreens.AUTH.name
                }
                if (loginStatus == LoginStatus.LOGGEDIN.name){
                    startDest = MainScreens.HOME.name
                }
                if (loginStatus != LoginStatus.WAITING.name){
                    NavHost(
                        navController = navController,
                        startDestination = startDest,
                        enterTransition = {
                            EnterTransition.None
                        },
                        exitTransition = {
                            ExitTransition.None
                        },
                    ) {
                        composable(route = MainScreens.AUTH.name) {
                            AuthScreen(navController, viewModel)
                        }
                        composable(route = MainScreens.HOME.name) {
                            HomeScreen(navController, viewModel)
                        }
                    }
                }


            }
        }
    }

    private fun requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val hasPermission = ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED

            if (!hasPermission) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    0,
                )
            }
        }
    }

    private fun requestFirebaseToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener {
            if (!it.isSuccessful) return@addOnCompleteListener
            val token = it.result
            Log.d("test", "token: $token")

            //SEND THIS TOKEN
            //EITHER USER ID OR TOKEN SEND TO
        }
    }


}
