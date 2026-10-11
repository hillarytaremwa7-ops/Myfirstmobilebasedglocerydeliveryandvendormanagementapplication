package com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model

enum class UserRole(val label: String, val description: String) {
    CUSTOMER("Customer", "Browse groceries, place orders, track deliveries"),
    VENDOR("Grocery Vendor", "Manage products, prices, inventory, & vendor orders"),
    RIDER("Boda Rider", "Deliver groceries from vendors to customers in Mbarara"),
    MIDDLEMAN("Middleman / Broker", "Connect customers with vendors & coordinate deliveries"),
    ADMIN("Administrator", "Manage users, approvals, disputes, & platform settings")
}
