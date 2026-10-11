package com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vendors")
data class Vendor(
    @PrimaryKey val id: String,
    val userId: String = "",
    val name: String,
    val category: String,
    val rating: Double,
    val address: String = "Mbarara Central Market, Block A",
    val phone: String,
    val status: AccountStatus = AccountStatus.APPROVED,
    val isOpen: Boolean = true,
    val commissionRateOverride: Double? = null
)
