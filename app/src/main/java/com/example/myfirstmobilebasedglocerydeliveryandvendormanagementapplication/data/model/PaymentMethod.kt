package com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model

enum class PaymentMethod(val displayName: String) {
    MOBILE_MONEY("Mobile Money (MTN / Airtel / M-Pesa)"),
    CASH_ON_DELIVERY("Cash on Delivery (COD)"),
    CARD("Credit / Debit Card"),
    WALLET("In-App Platform Wallet")
}

enum class PaymentStatus {
    PENDING,
    COMPLETED,
    FAILED,
    REFUNDED
}
