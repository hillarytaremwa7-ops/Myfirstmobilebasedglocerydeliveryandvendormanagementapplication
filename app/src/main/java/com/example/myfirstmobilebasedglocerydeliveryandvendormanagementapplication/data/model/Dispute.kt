package com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "disputes")
data class Dispute(
    @PrimaryKey val disputeId: String,
    val orderId: String,
    val reportedByUserId: String,
    val reporterName: String,
    val reporterRole: UserRole,
    val title: String,
    val description: String,
    val status: DisputeStatus = DisputeStatus.OPEN,
    val resolutionNote: String = "",
    val createdAt: Long = System.currentTimeMillis()
)

enum class DisputeStatus(val label: String) {
    OPEN("Open"),
    UNDER_REVIEW("Under Review"),
    RESOLVED("Resolved"),
    DISMISSED("Dismissed")
}
