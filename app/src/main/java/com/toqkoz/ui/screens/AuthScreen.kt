package com.toqkoz.ui.screens

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.toqkoz.ui.screens.auth.AboutScreen
import com.toqkoz.ui.screens.auth.LoginScreen
import com.toqkoz.ui.screens.auth.NewPasswordScreen
import com.toqkoz.ui.screens.auth.PasswordScreen
import com.toqkoz.ui.screens.auth.ResetLoginScreen
import com.toqkoz.ui.screens.auth.ResetPasswordScreen
import com.toqkoz.ui.theme.ToqkozTheme

enum class AuthScreens {
    LOGIN,
    PASSWORD,
    RESET_LOGIN,
    RESET_PASSWORD,
    NEW_PASSWORD,
    ABOUT,
}

@Composable
fun AuthScreen(rootNavController: NavHostController) {
    val authNavController = rememberNavController()
    var selectedRoute by remember { mutableStateOf(AuthScreens.LOGIN) }

    NavHost(
        navController = authNavController,
        startDestination = AuthScreens.LOGIN.name,
        enterTransition = {
            EnterTransition.None
        },
        exitTransition = {
            ExitTransition.None
        },
    ) {
        composable(route = AuthScreens.LOGIN.name) {
            LoginScreen(authNavController)
        }
        composable(route = AuthScreens.PASSWORD.name) {
            PasswordScreen(rootNavController, authNavController)
        }
        composable(route = AuthScreens.RESET_LOGIN.name) {
            ResetLoginScreen(authNavController)
        }
        composable(route = AuthScreens.RESET_PASSWORD.name) {
            ResetPasswordScreen(authNavController)
        }
        composable(route = AuthScreens.NEW_PASSWORD.name) {
            NewPasswordScreen(authNavController)
        }
        composable(route = AuthScreens.ABOUT.name) {
            AboutScreen(authNavController)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AuthScreenPreview() {
    ToqkozTheme {
        AuthScreen(rememberNavController())
    }
}
