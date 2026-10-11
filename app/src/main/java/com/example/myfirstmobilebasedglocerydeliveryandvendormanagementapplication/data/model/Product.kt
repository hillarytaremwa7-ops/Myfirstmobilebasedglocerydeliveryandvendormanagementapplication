package com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class Product(
    @PrimaryKey val id: String,
    val vendorId: String,
    val vendorName: String = "Mbarara Fresh Vendor",
    val name: String,
    val category: String,
    val unit: String,
    val price: Double, // In UGX (Ugandan Shillings)
    val description: String,
    val stockQuantity: Int,
    val iconName: String,
    val imageUrl: String = "",
    val isAvailable: Boolean = true
)
