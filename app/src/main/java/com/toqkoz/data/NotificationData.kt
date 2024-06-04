package com.toqkoz.data

data class NotificationData(
    val id: String = "",
    val title: String = "",
    val description: String ="",
    val created_at: String ="",
    val latest_at: String ="",
    val status: String ="",
    val count: Int = 0
)