package com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "orders")
data class Order(
    @PrimaryKey val id: String,
    val customerId: String = "cust1",
    val customerName: String,
    val customerPhone: String,
    val deliveryAddress: String,
    val vendorId: String,
    val vendorName: String,
    val middlemanId: String? = null,
    val middlemanName: String? = null,
    val riderId: String? = null,
    val riderName: String? = null,
    val itemsSummary: String,
    val subtotal: Double, // UGX
    val deliveryFee: Double = 5000.0, // 5,000 UGX
    val serviceFee: Double = 1000.0, // 1,000 UGX
    val middlemanFee: Double = 0.0,
    val commissionAmount: Double = 0.0,
    val vendorPayout: Double = 0.0,
    val totalAmount: Double, // UGX
    val paymentMethod: PaymentMethod,
    val paymentStatus: PaymentStatus,
    val orderStatus: OrderStatus = OrderStatus.PENDING,
    val deliveryStatus: DeliveryStatus = DeliveryStatus.UNASSIGNED,
    val deliveryInstructions: String = "",
    val timestamp: Long = System.currentTimeMillis()
)
