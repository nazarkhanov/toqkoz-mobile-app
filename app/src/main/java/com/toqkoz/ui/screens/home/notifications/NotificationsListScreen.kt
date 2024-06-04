package com.toqkoz.ui.screens.home.notifications

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.toqkoz.MyViewModel
import com.toqkoz.data.NotificationData
import com.toqkoz.ui.components.Tabbar
import com.toqkoz.ui.screens.home.NotificationsScreens
import com.toqkoz.ui.theme.ToqkozTheme
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationsListScreen(navController: NavHostController, viewModel: MyViewModel) {

    val notificationsList by viewModel.notificationsList.collectAsState()
    val pullRefreshState = rememberPullToRefreshState()
    val isLoading by viewModel.isLoading.collectAsState()

    Column(modifier = Modifier.padding(bottom= 60.dp)){
        Tabbar(title = "Оповещения")

        Box(modifier = Modifier
            .nestedScroll(pullRefreshState.nestedScrollConnection)){
            LazyColumn(contentPadding = PaddingValues()){
                items(notificationsList){notification->
                    NotificationsListElement(navController = navController,notification,viewModel )
                }
            }

            if (pullRefreshState.isRefreshing){
                LaunchedEffect(true){
                    viewModel.updateNotifications()

                }
            }
            LaunchedEffect(isLoading){
                if (isLoading){
                    pullRefreshState.startRefresh()
                }else{
                    pullRefreshState.endRefresh()
                }
            }
            PullToRefreshContainer(state = pullRefreshState,
                modifier = Modifier.align(Alignment.TopCenter),
                containerColor = Color.Transparent,
                contentColor = Color.Black,
            )

        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NotificationsListElement(navController: NavHostController, notification: NotificationData, viewModel: MyViewModel){

    OutlinedCard (
        onClick = { navController.navigate(NotificationsScreens.DETAIL.name);
                    viewModel.selectNotification(notification)
                  },
        modifier = Modifier
            .padding(top = 16.dp, bottom = 16.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
    ) {
        Column (
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 16.dp)
                .fillMaxWidth()
        ) {
            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .background(MaterialTheme.colorScheme.error)
                        .padding(vertical = 6.dp, horizontal = 8.dp),
                ) {
                    Text(
                        text = notification.status,
                        style = TextStyle(
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onError
                        )
                    )
                }

                Text(
                    text = timeAgo(notification.latest_at),
                    style = TextStyle(fontSize = 12.sp)
                )
            }

            Column {
                Text(
                    text = notification.title,
                    modifier = Modifier.padding(vertical = 8.dp),
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = 18.sp
                    )
                )

                Text(
                    text = notification.count.toString()+" оповещений",
                    modifier = Modifier.padding(bottom = 8.dp),
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = 12.sp
                    )
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun timeAgo(dateString: String): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
    val date = LocalDateTime.parse(dateString, formatter)

    val currentTime = LocalDateTime.now()
    val diffSeconds = date.toEpochSecond(ZoneOffset.UTC) - currentTime.toEpochSecond(ZoneOffset.UTC)

    return when {
        diffSeconds < 60 -> "1 мин назад"
        diffSeconds < 3600 -> "${diffSeconds / 60} мин назад"
        diffSeconds < 86400 -> "${diffSeconds / 3600} ч назад"
        else -> date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
    }
}


@Preview(showBackground = true)
@Composable
fun NotificationsListScreenPreview() {
    ToqkozTheme {
//        NotificationsListScreen(rememberNavController(), viewModel = MyViewModel())
    }
}
