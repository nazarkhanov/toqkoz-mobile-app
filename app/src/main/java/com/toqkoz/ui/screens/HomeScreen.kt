package com.toqkoz.ui.screens

import android.annotation.SuppressLint
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.toqkoz.MyViewModel
import com.toqkoz.ui.screens.home.MapScreen
import com.toqkoz.ui.screens.home.NotificationsScreen
import com.toqkoz.ui.screens.home.SettingsScreen
import com.toqkoz.ui.screens.home.TrackersScreen
import com.toqkoz.ui.theme.ToqkozTheme

enum class HomeScreens {
    NOTIFICATIONS,
    TRACKERS,
    MAP,
    SETTINGS,
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
 fun HomeScreen(rootNavController: NavHostController, viewModel: MyViewModel) {
    val homeNavController = rememberNavController()
    var selectedRoute by remember {mutableStateOf(HomeScreens.NOTIFICATIONS) }

    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        Scaffold(
            bottomBar = {
                val color = MaterialTheme.colorScheme.outlineVariant

                Surface(
                    modifier = Modifier.drawBehind {
                        drawLine(
                            color = color,
                            start = Offset(0f, 0f),
                            end = Offset(size.width, 0f),
                            strokeWidth = 1.dp.toPx()
                        )
                    }
                ) {
                    NavigationBar(
                        containerColor = MaterialTheme.colorScheme.background,
                        tonalElevation = 0.dp,
                        modifier = Modifier.height(64.dp),
                    ) {
                        NavigationItem(
                            navController = homeNavController,
                            rowScope = this,
                            route = HomeScreens.NOTIFICATIONS,
                            onClick = { selectedRoute = HomeScreens.NOTIFICATIONS },
                            isSelected = selectedRoute == HomeScreens.NOTIFICATIONS,
                            title = "Оповещения",
                            selectedIcon = Icons.Filled.Notifications,
                            unselectedIcon = Icons.Outlined.Notifications,
                            hasNews = false,
                            badgeCount = 5
                        )
                        NavigationItem(
                            navController = homeNavController,
                            rowScope = this,
                            route = HomeScreens.TRACKERS,
                            onClick = { selectedRoute = HomeScreens.TRACKERS },
                            isSelected = selectedRoute == HomeScreens.TRACKERS,
                            title = "Устройства",
                            selectedIcon = Icons.Filled.Info,
                            unselectedIcon = Icons.Outlined.Info,
                            hasNews = false,
                            badgeCount = null
                        )
                        NavigationItem(
                            navController = homeNavController,
                            rowScope = this,
                            route = HomeScreens.MAP,
                            onClick = { selectedRoute = HomeScreens.MAP },
                            isSelected = selectedRoute == HomeScreens.MAP,
                            title = "Карта",
                            selectedIcon = Icons.Filled.LocationOn,
                            unselectedIcon = Icons.Outlined.LocationOn,
                            hasNews = false,
                            badgeCount = null
                        )
                        NavigationItem(
                            navController = homeNavController,
                            rowScope = this,
                            route = HomeScreens.SETTINGS,
                            onClick = { selectedRoute = HomeScreens.SETTINGS },
                            isSelected = selectedRoute == HomeScreens.SETTINGS,
                            title = "Настройки",
                            selectedIcon = Icons.Filled.Settings,
                            unselectedIcon = Icons.Outlined.Settings,
                            hasNews = false,
                            badgeCount = null
                        )
                    }
                }
            }
        ) {
            NavHost(
                navController = homeNavController,
                startDestination = HomeScreens.NOTIFICATIONS.name,
                enterTransition = {
                    EnterTransition.None
                },
                exitTransition = {
                    ExitTransition.None
                },
            ) {
                composable(route = HomeScreens.NOTIFICATIONS.name) {
                    NotificationsScreen(viewModel)
                }
                composable(route = HomeScreens.TRACKERS.name) {
                    TrackersScreen(viewModel)
                }
                composable(route = HomeScreens.MAP.name) {
                    MapScreen()
                }
                composable(route = HomeScreens.SETTINGS.name) {
                    SettingsScreen(rootNavController,viewModel)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    ToqkozTheme {
//        HomeScreen(rememberNavController(), viewModel= MyViewModel())
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationItem(
    navController: NavHostController,
    rowScope: RowScope,
    route: HomeScreens,
    onClick: () -> Unit,
    isSelected: Boolean,
    title: String,
    selectedIcon: ImageVector,
    unselectedIcon: ImageVector,
    hasNews: Boolean,
    badgeCount: Int?
) {
    rowScope.NavigationBarItem(
        selected = isSelected,
        onClick = {
            onClick()
            navController.navigate(route = route.name)
        },
        label = {
            Text(text = title, style = TextStyle(fontSize = 12.sp))
        },
        icon = {
            BadgedBox(
                badge = {
                    if (badgeCount != null) {
                        Badge (
                            containerColor = MaterialTheme.colorScheme.error,
                        ) {
                            Text(text = badgeCount.toString())
                        }
                    } else if (hasNews) {
                        Badge(
                            containerColor = MaterialTheme.colorScheme.error,
                        )
                    }
                }
            ) {
                Icon(
                    imageVector = if (isSelected) selectedIcon else unselectedIcon,
                    contentDescription = title
                )
            }
        },
        colors = NavigationBarItemDefaults.colors(indicatorColor = MaterialTheme.colorScheme.inverseOnSurface)
    )
}
