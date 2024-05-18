package com.toqkoz.ui.screens.home

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
import com.toqkoz.ui.components.DataPoint
import com.toqkoz.ui.components.LineChart
import com.toqkoz.ui.components.Tabbar
import com.toqkoz.ui.theme.ToqkozTheme
import kotlinx.collections.immutable.persistentListOf

@Composable
fun TrackersScreen() {
    val chartData = persistentListOf(
        DataPoint(220.0),
        DataPoint(224.0),
        DataPoint(219.0),
        DataPoint(222.0),
        DataPoint(220.0),
        DataPoint(224.0),
        DataPoint(219.0),
        DataPoint(222.0),
    )

    Column(modifier = Modifier.fillMaxSize()) {
        Tabbar(title = "Устройства")

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
}

@Preview(showBackground = true)
@Composable
fun TrackersScreenPreview() {
    ToqkozTheme {
        TrackersScreen()
    }
}
