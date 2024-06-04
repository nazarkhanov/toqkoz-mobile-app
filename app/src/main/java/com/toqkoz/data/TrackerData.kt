package com.toqkoz.data

import com.toqkoz.ui.components.DataPoint
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

data class TrackerData(
    val id: String = "",
    val title: String = "",
    val description: String ="",
    val created_at: String ="",
    val latest_at: String ="",
    val status: String ="",
    val count: Int = 0,
    val chartData: PersistentList<DataPoint> = persistentListOf(DataPoint(220.0),
        DataPoint(224.0),
        DataPoint(219.0),
        DataPoint(222.0),
        DataPoint(220.0),
        DataPoint(210.0),
        DataPoint(219.0),
        DataPoint(225.0))
)