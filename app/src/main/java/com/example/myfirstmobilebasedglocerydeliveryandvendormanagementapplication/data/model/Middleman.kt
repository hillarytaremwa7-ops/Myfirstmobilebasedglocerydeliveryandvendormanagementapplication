package com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "middlemen")
data class Middleman(
    @PrimaryKey val middlemanId: String,
    val userId: String,
    val fullName: String,
    val phone: String,
    val serviceArea: String = "Mbarara City & Central Market",
    val description: String = "Professional Grocery Agent & Market Broker",
    val commissionRate: Double = 0.05, // 5%
    val verificationStatus: AccountStatus = AccountStatus.APPROVED,
    val availabilityStatus: AvailabilityStatus = AvailabilityStatus.ONLINE,
    val totalTransactions: Int = 0,
    val totalCommission: Double = 0.0,
    val createdAt: Long = System.currentTimeMillis()
)
