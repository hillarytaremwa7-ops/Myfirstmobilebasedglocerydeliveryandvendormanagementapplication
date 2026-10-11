package com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notifications")
data class NotificationItem(
    @PrimaryKey val notificationId: String,
    val userId: String,
    val title: String,
    val message: String,
    val channel: String = "SMS & EMAIL",
    val type: String = "INFO",
    val relatedOrderId: String = "",
    val isRead: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
)
