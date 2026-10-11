package com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reviews")
data class Review(
    @PrimaryKey val reviewId: String,
    val customerId: String,
    val customerName: String,
    val vendorId: String = "",
    val riderId: String = "",
    val orderId: String = "",
    val rating: Int, // 1 to 5
    val comment: String,
    val createdAt: Long = System.currentTimeMillis()
)
