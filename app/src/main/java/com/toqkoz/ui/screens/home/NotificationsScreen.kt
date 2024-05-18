package com.toqkoz.ui.screens.home

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.toqkoz.ui.screens.home.notifications.NotificationDetailScreen
import com.toqkoz.ui.screens.home.notifications.NotificationsListScreen
import com.toqkoz.ui.theme.ToqkozTheme

enum class NotificationsScreens {
    LIST,
    DETAIL,
}

@Composable
fun NotificationsScreen() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NotificationsScreens.LIST.name,
        enterTransition = {
            EnterTransition.None
        },
        exitTransition = {
            ExitTransition.None
        },
    ) {
        composable(route = NotificationsScreens.LIST.name) {
            NotificationsListScreen(navController)
        }
        composable(route = NotificationsScreens.DETAIL.name) {
            NotificationDetailScreen(navController)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotificationsScreenPreview() {
    ToqkozTheme {
        NotificationsScreen()
    }
}
