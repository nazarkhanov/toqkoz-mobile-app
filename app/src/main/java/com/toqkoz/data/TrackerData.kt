package com.toqkoz.data

import com.toqkoz.ui.components.DataPoint
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

data class TrackerData(
    val id: String = "",
    val title: String = "",
    val description: String ="",
    val status: String ="",
    val chartData: PersistentList<DataPoint> = persistentListOf()
)