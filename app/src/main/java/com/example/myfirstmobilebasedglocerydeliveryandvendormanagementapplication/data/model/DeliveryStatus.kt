package com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model

enum class DeliveryStatus(val label: String, val stepIndex: Int) {
    UNASSIGNED("Unassigned", 0),
    ASSIGNED("Rider Assigned", 1),
    ACCEPTED_BY_RIDER("Accepted by Rider", 2),
    GOING_TO_PICKUP("Going to Pickup", 3),
    PICKED_UP("Picked Up from Vendor", 4),
    GOING_TO_CUSTOMER("Out for Delivery", 5),
    DELIVERED("Delivered to Customer", 6),
    REJECTED("Rejected by Rider", -1)
}
