package com.toqkoz.ui.screens.home

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.toqkoz.MyViewModel
import com.toqkoz.ui.components.DataPoint
import com.toqkoz.ui.components.LineChart
import com.toqkoz.ui.components.Tabbar
import com.toqkoz.ui.screens.home.notifications.NotificationDetailScreen
import com.toqkoz.ui.screens.home.notifications.NotificationsListScreen
import com.toqkoz.ui.screens.home.trackers.TrackerDetailScreen
import com.toqkoz.ui.screens.home.trackers.TrackersListScreen
import com.toqkoz.ui.theme.ToqkozTheme
import kotlinx.collections.immutable.persistentListOf
enum class TrackersScreens {
    LIST,
    DETAIL,
}
@Composable
fun TrackersScreen(viewModel:MyViewModel) {

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
        composable(route = TrackersScreens.LIST.name) {
            TrackersListScreen(navController, viewModel)
        }
        composable(route = TrackersScreens.DETAIL.name) {
            TrackerDetailScreen(navController, viewModel )
        }
    }

}



@Preview(showBackground = true)
@Composable
fun TrackersScreenPreview() {
    ToqkozTheme {
        TrackersScreen(viewModel = MyViewModel())
    }
}
