package com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "customer_requests")
data class CustomerRequest(
    @PrimaryKey val requestId: String,
    val customerId: String,
    val customerName: String,
    val customerPhone: String,
    val requestedProducts: String,
    val preferredCategory: String,
    val deliveryLocation: String,
    val preferredVendorId: String = "",
    val middlemanId: String = "",
    val status: RequestStatus = RequestStatus.PENDING,
    val createdAt: Long = System.currentTimeMillis()
)

enum class RequestStatus(val label: String) {
    PENDING("Pending Middleman"),
    ACCEPTED("Accepted by Middleman"),
    VENDOR_FOUND("Vendor Sourced"),
    ORDER_CREATED("Order Created"),
    COMPLETED("Completed"),
    CANCELLED("Cancelled")
}
