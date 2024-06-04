package com.toqkoz.ui.screens.home.trackers

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.toqkoz.ui.components.LineChart
import com.toqkoz.ui.components.Tabbar
import com.toqkoz.ui.screens.home.notifications.PairInfo
import com.toqkoz.ui.theme.ToqkozTheme
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrackerDetailScreen(navController: NavHostController, viewModel:MyViewModel){

    val tracker:TrackerData = viewModel.selectedTracker.value
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = {navController.popBackStack()}) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Назад",
                        )
                    }
                }
            )
        },
    ) { innerPadding ->
        Column(modifier = Modifier.padding(top = 60.dp)) {
            Chart(viewModel.selectedTracker.value.chartData)
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(innerPadding)
                    .padding(horizontal = 20.dp, vertical = 4.dp),
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

                Text(
                    text = tracker.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp),
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = 20.sp
                    )
                )
                PairInfo("Создано", tracker.created_at)
                PairInfo("Последнее оповещение", tracker.latest_at)
                PairInfo("Детали", tracker.description)
            }
        }


    }
}



@Composable
fun Chart(chartData:PersistentList<DataPoint>){
//    val chartData = persistentListOf(
//        DataPoint(220.0),
//        DataPoint(224.0),
//        DataPoint(219.0),
//        DataPoint(222.0),
//        DataPoint(220.0),
//        DataPoint(0.0),
//        DataPoint(219.0),
//        DataPoint(222.0),
//    )

        Column (
            modifier = Modifier
                .fillMaxWidth()
                .requiredHeight(240.dp)
                .padding(vertical = 20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.End

        ) {
            LineChart(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                data = chartData,
                graphColor = MaterialTheme.colorScheme.primary,
                showDashedLine = true,
                showYLabels = true
            )
        }

}