package com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey val userId: String,
    val firstName: String = "",
    val lastName: String = "",
    val fullName: String = "$firstName $lastName".trim(),
    val email: String,
    val phone: String,
    val role: UserRole,
    val address: String = "Mbarara City, Uganda",
    val profileImage: String = "",
    val enableSmsNotifications: Boolean = true,
    val enableEmailNotifications: Boolean = true,
    val accountStatus: AccountStatus = AccountStatus.APPROVED,
    val createdAt: Long = System.currentTimeMillis()
)

enum class AccountStatus {
    PENDING,
    APPROVED,
    SUSPENDED
}
