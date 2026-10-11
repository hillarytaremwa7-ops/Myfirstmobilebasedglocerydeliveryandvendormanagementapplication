package com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "delivery_requests")
data class DeliveryRequest(
    @PrimaryKey val deliveryRequestId: String,
    val orderId: String,
    val riderId: String,
    val vendorId: String,
    val vendorName: String,
    val customerId: String,
    val customerName: String,
    val pickupLocation: String = "Mbarara Central Market",
    val deliveryLocation: String,
    val deliveryFee: Double = 5000.0, // 5,000 UGX
    val distance: String = "2.5 km",
    val orderValue: Double,
    val requestStatus: DeliveryStatus = DeliveryStatus.ASSIGNED,
    val assignedAt: Long = System.currentTimeMillis(),
    val acceptedAt: Long? = null,
    val pickedUpAt: Long? = null,
    val deliveredAt: Long? = null
)
