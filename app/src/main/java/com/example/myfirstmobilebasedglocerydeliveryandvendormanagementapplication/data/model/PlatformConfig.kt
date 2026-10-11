package com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "platform_config")
data class PlatformConfig(
    @PrimaryKey val id: Int = 1,
    val defaultCommissionRate: Double = 0.10, // 10%
    val serviceFeeFlat: Double = 1000.0, // 1,000 UGX
    val defaultDeliveryFee: Double = 5000.0, // 5,000 UGX
    val totalCommissionCollected: Double = 150000.0 // UGX
)
