package com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "riders")
data class Rider(
    @PrimaryKey val riderId: String,
    val userId: String,
    val fullName: String,
    val phone: String,
    val motorcycleNumber: String,
    val licenseNumber: String,
    val nationalId: String,
    val profileImage: String = "",
    val availabilityStatus: AvailabilityStatus = AvailabilityStatus.ONLINE,
    val verificationStatus: AccountStatus = AccountStatus.APPROVED,
    val currentLocation: String = "Mbarara Central Market",
    val totalDeliveries: Int = 0,
    val totalEarnings: Double = 0.0,
    val createdAt: Long = System.currentTimeMillis()
)

enum class AvailabilityStatus(val label: String) {
    ONLINE("Online"),
    OFFLINE("Offline"),
    BUSY("Busy")
}
