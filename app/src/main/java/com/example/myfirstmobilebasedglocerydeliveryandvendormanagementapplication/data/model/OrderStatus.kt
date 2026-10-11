package com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model

enum class OrderStatus(val label: String, val stepIndex: Int) {
    PENDING("Order Placed", 0),
    ACCEPTED("Accepted by Vendor", 1),
    PREPARING("Preparing Items", 2),
    READY_FOR_DELIVERY("Ready for Driver", 3),
    OUT_FOR_DELIVERY("Out for Delivery", 4),
    DELIVERED("Delivered", 5),
    CANCELLED("Cancelled", -1)
}
