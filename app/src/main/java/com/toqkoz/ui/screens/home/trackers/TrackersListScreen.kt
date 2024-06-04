package com.toqkoz.ui.screens.home.trackers

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
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
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.toqkoz.MyViewModel
import com.toqkoz.data.NotificationData
import com.toqkoz.data.TrackerData
import com.toqkoz.ui.components.DataPoint
import com.toqkoz.ui.components.Tabbar
import com.toqkoz.ui.screens.home.NotificationsScreens

import kotlinx.collections.immutable.persistentListOf

val trackersList = listOf<TrackerData>(
    TrackerData(
        id = "jnkjnvkdnfdvad",
        title = "Устройство номер 1",
        status = "Неисправность",
        description = "Some description",
        chartData = persistentListOf(
            DataPoint(220.0),
            DataPoint(224.0),
            DataPoint(219.0),
            DataPoint(222.0),
            DataPoint(220.0),
            DataPoint(0.0),
            DataPoint(219.0),
            DataPoint(500.0),)
        ),
    TrackerData(
        id = "jnkjnvkdnfdvad",
        title = "Устройство номер 2",
        status = "Неисправность",
        description = "Some description",
        chartData = persistentListOf(
            DataPoint(220.0),
            DataPoint(224.0),
            DataPoint(219.0),
            DataPoint(222.0),
            DataPoint(220.0),
            DataPoint(184.0),
            DataPoint(219.0),
            DataPoint(222.0),)
    ),TrackerData(
        id = "jnkjnvkdnfdvad",
        title = "Устройство номер 3",
        status = "Неисправность",
        description = "Some description",
        chartData = persistentListOf(
            DataPoint(220.0),
            DataPoint(224.0),
            DataPoint(219.0),
            DataPoint(222.0),
            DataPoint(220.0),
            DataPoint(230.0),
            DataPoint(219.0),
            DataPoint(222.0),)
    )
)

@Composable
fun TrackersListScreen(navController: NavHostController, viewModel: MyViewModel){


    Column(modifier = Modifier.padding(bottom= 60.dp)){
        Tabbar(title = "Устройства")

            LazyColumn(contentPadding = PaddingValues()){
                items(trackersList){ tracker->
                    TrackersListElement(navController = navController,tracker,viewModel )
                }
            }

    }

}


@Composable
fun TrackersListElement(navController: NavHostController, tracker: TrackerData, viewModel: MyViewModel){

    OutlinedCard (
        onClick = { navController.navigate(NotificationsScreens.DETAIL.name);
            viewModel.selectTracker(tracker)
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
                        text = tracker.status,
                        style = TextStyle(
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onError
                        )
                    )
                }

//                Text(
//                    text = "10 минут назад",
//                    style = TextStyle(fontSize = 12.sp)
//                )
            }

            Column {
                Text(
                    text = tracker.title,
                    modifier = Modifier.padding(vertical = 8.dp),
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = 18.sp
                    )
                )

//                Text(
//                    text = notification.count.toString()+" оповещений",
//                    modifier = Modifier.padding(bottom = 8.dp),
//                    style = TextStyle(
//                        color = MaterialTheme.colorScheme.onBackground,
//                        fontSize = 12.sp
//                    )
//                )
            }
        }
    }
}

@Preview
@Composable
fun TrackersListScreenPreview(){
    TrackersListScreen(rememberNavController(), viewModel = MyViewModel())
}