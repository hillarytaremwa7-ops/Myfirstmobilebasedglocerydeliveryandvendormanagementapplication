package com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.local

import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.AccountStatus
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.AvailabilityStatus
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.CustomerRequest
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.DeliveryRequest
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.DeliveryStatus
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.Dispute
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.DisputeStatus
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.Middleman
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.NotificationItem
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.Order
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.OrderStatus
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.PaymentMethod
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.PaymentStatus
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.PlatformConfig
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.Product
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.RequestStatus
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.Rider
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.User
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.UserRole
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.Vendor

object SampleData {

    val defaultConfig = PlatformConfig(
        id = 1,
        defaultCommissionRate = 0.05, // 5% Commission
        serviceFeeFlat = 1000.0, // 1,000 UGX
        defaultDeliveryFee = 5000.0, // 5,000 UGX
        totalCommissionCollected = 185000.0 // 185,000 UGX
    )

    val sampleUsers = listOf(
        User(
            userId = "u_cust1",
            firstName = "Grace",
            lastName = "Atuhaire",
            fullName = "Grace Atuhaire",
            email = "grace@example.com",
            phone = "+256 772 123456",
            role = UserRole.CUSTOMER,
            address = "Kikoni, Mbarara City",
            enableSmsNotifications = true,
            enableEmailNotifications = true
        ),
        User(
            userId = "u_vendor1",
            firstName = "Mama",
            lastName = "Sarah",
            fullName = "Mama Sarah Groceries",
            email = "mamasarah@market.co.ug",
            phone = "+256 701 987654",
            role = UserRole.VENDOR,
            address = "Mbarara Central Market, Stalls A12-A15",
            enableSmsNotifications = true,
            enableEmailNotifications = true
        ),
        User(
            userId = "u_rider1",
            firstName = "John",
            lastName = "Boda",
            fullName = "John Boda Services",
            email = "john.boda@mbarara.com",
            phone = "+256 782 554433",
            role = UserRole.RIDER,
            address = "Mbarara Stage 1",
            enableSmsNotifications = true,
            enableEmailNotifications = true
        ),
        User(
            userId = "u_mid1",
            firstName = "Market",
            lastName = "Connect",
            fullName = "Mbarara Market Connect",
            email = "connect@mbararamarket.ug",
            phone = "+256 750 112233",
            role = UserRole.MIDDLEMAN,
            address = "Mbarara Central Market Office B",
            enableSmsNotifications = true,
            enableEmailNotifications = true
        ),
        User(
            userId = "u_admin1",
            firstName = "Taremwa",
            lastName = "Hillary",
            fullName = "Taremwa Hillary",
            email = "htaremwabit24@usj.ac.ug",
            phone = "+256 764 176632",
            role = UserRole.ADMIN,
            address = "Mbarara City Council Market Admin Office",
            enableSmsNotifications = true,
            enableEmailNotifications = true
        )
    )

    val sampleVendors = listOf(
        Vendor(
            id = "v1",
            userId = "u_vendor1",
            name = "Mbarara Fresh Groceries",
            category = "Vegetables & Matooke",
            rating = 4.9,
            address = "Mbarara Central Market, Block A Stall 12",
            phone = "+256 701 987654",
            isOpen = true
        ),
        Vendor(
            id = "v2",
            userId = "u_vendor2",
            name = "Ankole Organic Dairy & Farm",
            category = "Dairy & Eggs",
            rating = 4.8,
            address = "Mbarara Central Market, Dairy Section Stall 5",
            phone = "+256 772 889900",
            isOpen = true
        ),
        Vendor(
            id = "v3",
            userId = "u_vendor3",
            name = "Rwampara Cereals & Pulses",
            category = "Cereals & Pulses",
            rating = 4.7,
            address = "Mbarara Central Market, Grain Store 8",
            phone = "+256 782 334455",
            isOpen = true
        ),
        Vendor(
            id = "v4",
            userId = "u_vendor4",
            name = "Kakinga Butchery & Meats",
            category = "Meat",
            rating = 4.6,
            address = "Mbarara Central Market, Butchery Row Stall 3",
            phone = "+256 700 112244",
            isOpen = true
        )
    )

    val sampleProducts = listOf(
        Product(
            id = "p1",
            vendorId = "v1",
            vendorName = "Mbarara Fresh Groceries",
            name = "Tomatoes",
            category = "Vegetables",
            unit = "1 kg",
            price = 3000.0,
            description = "Freshly picked red tomatoes from local Mbarara farms.",
            stockQuantity = 50,
            iconName = "vegetables",
            imageUrl = "https://images.unsplash.com/photo-1592924357228-91a4daadcfea?w=500&q=80"
        ),
        Product(
            id = "p2",
            vendorId = "v1",
            vendorName = "Mbarara Fresh Groceries",
            name = "Onions",
            category = "Vegetables",
            unit = "1 kg",
            price = 4000.0,
            description = "Dry red bulb onions essential for all cooking.",
            stockQuantity = 40,
            iconName = "vegetables",
            imageUrl = "https://images.unsplash.com/photo-1618512496248-a07fe83aa8cf?w=500&q=80"
        ),
        Product(
            id = "p3",
            vendorId = "v1",
            vendorName = "Mbarara Fresh Groceries",
            name = "Matooke",
            category = "Tubers",
            unit = "1 Bunch",
            price = 25000.0,
            description = "Fresh green plantains (Matooke) straight from Isingiro farms.",
            stockQuantity = 20,
            iconName = "tubers",
            imageUrl = "https://images.unsplash.com/photo-1571771894821-ce9b6c11b08e?w=500&q=80"
        ),
        Product(
            id = "p4",
            vendorId = "v1",
            vendorName = "Mbarara Fresh Groceries",
            name = "Irish Potatoes",
            category = "Tubers",
            unit = "1 Heap (~3kg)",
            price = 10000.0,
            description = "Clean, firm Irish potatoes from Kabale/Rubanda region.",
            stockQuantity = 30,
            iconName = "tubers",
            imageUrl = "https://images.unsplash.com/photo-1518977676601-b53f82aba655?w=500&q=80"
        ),
        Product(
            id = "p5",
            vendorId = "v1",
            vendorName = "Mbarara Fresh Groceries",
            name = "Cabbage",
            category = "Vegetables",
            unit = "1 Piece",
            price = 2000.0,
            description = "Crisp, green fresh head cabbage.",
            stockQuantity = 25,
            iconName = "vegetables",
            imageUrl = "https://images.unsplash.com/photo-1594282486552-05b4d80fbb9f?w=500&q=80"
        ),
        Product(
            id = "p6",
            vendorId = "v1",
            vendorName = "Mbarara Fresh Groceries",
            name = "Sukuma Wiki",
            category = "Vegetables",
            unit = "1 Bundle",
            price = 1000.0,
            description = "Freshly harvested green collard greens.",
            stockQuantity = 40,
            iconName = "vegetables",
            imageUrl = "https://images.unsplash.com/photo-1628773822503-930a85818988?w=500&q=80"
        ),
        Product(
            id = "p7",
            vendorId = "v1",
            vendorName = "Mbarara Fresh Groceries",
            name = "Avocado",
            category = "Fruits",
            unit = "1 Piece",
            price = 1000.0,
            description = "Large, rich, butter avocado.",
            stockQuantity = 60,
            iconName = "fruits",
            imageUrl = "https://images.unsplash.com/photo-1523049673857-eb18f1d7b578?w=500&q=80"
        ),
        Product(
            id = "p8",
            vendorId = "v1",
            vendorName = "Mbarara Fresh Groceries",
            name = "Mangoes",
            category = "Fruits",
            unit = "1 kg",
            price = 5000.0,
            description = "Sweet local mangoes packed with vitamin C.",
            stockQuantity = 35,
            iconName = "fruits",
            imageUrl = "https://images.unsplash.com/photo-1553279768-865429fa0078?w=500&q=80"
        ),
        Product(
            id = "p9",
            vendorId = "v3",
            vendorName = "Rwampara Cereals & Pulses",
            name = "Yellow Beans",
            category = "Pulses",
            unit = "1 kg",
            price = 5000.0,
            description = "Sorted high-quality yellow dry beans.",
            stockQuantity = 80,
            iconName = "pulses",
            imageUrl = "https://images.unsplash.com/photo-1551462147-ff29053bfc14?w=500&q=80"
        ),
        Product(
            id = "p10",
            vendorId = "v3",
            vendorName = "Rwampara Cereals & Pulses",
            name = "Super Rice",
            category = "Cereals",
            unit = "1 kg",
            price = 4500.0,
            description = "Aromatic white long-grain Super Rice.",
            stockQuantity = 100,
            iconName = "cereals",
            imageUrl = "https://images.unsplash.com/photo-1586201375761-83865001e31c?w=500&q=80"
        ),
        Product(
            id = "p11",
            vendorId = "v3",
            vendorName = "Rwampara Cereals & Pulses",
            name = "Maize Flour (Posho)",
            category = "Cereals",
            unit = "1 kg",
            price = 3000.0,
            description = "Fine white refined maize flour (Grade 1).",
            stockQuantity = 120,
            iconName = "cereals",
            imageUrl = "https://images.unsplash.com/photo-1586201375761-83865001e31c?w=500&q=80"
        ),
        Product(
            id = "p12",
            vendorId = "v2",
            vendorName = "Ankole Organic Dairy & Farm",
            name = "Fresh Milk",
            category = "Dairy Products",
            unit = "1 Liter",
            price = 2000.0,
            description = "Pure pasteurized Ankole farm cow milk.",
            stockQuantity = 50,
            iconName = "dairy",
            imageUrl = "https://images.unsplash.com/photo-1563636619-e9143da7973b?w=500&q=80"
        ),
        Product(
            id = "p13",
            vendorId = "v2",
            vendorName = "Ankole Organic Dairy & Farm",
            name = "Local Eggs",
            category = "Eggs",
            unit = "1 Tray (30 Eggs)",
            price = 14000.0,
            description = "Fresh farm eggs from pasture-fed hens.",
            stockQuantity = 20,
            iconName = "eggs",
            imageUrl = "https://images.unsplash.com/photo-1582722872445-44dc5f7e3c8f?w=500&q=80"
        )
    )

    val sampleRiders = listOf(
        Rider(
            riderId = "r1",
            userId = "u_rider1",
            fullName = "John Boda Services",
            phone = "+256 782 554433",
            motorcycleNumber = "UFE 432B",
            licenseNumber = "DL-882319",
            nationalId = "CM91029384812",
            availabilityStatus = AvailabilityStatus.ONLINE,
            verificationStatus = AccountStatus.APPROVED,
            currentLocation = "Mbarara Central Stage",
            totalDeliveries = 48,
            totalEarnings = 240000.0
        ),
        Rider(
            riderId = "r2",
            userId = "u_rider2",
            fullName = "Moses Boda Express",
            phone = "+256 703 112233",
            motorcycleNumber = "UFG 812C",
            licenseNumber = "DL-901124",
            nationalId = "CM89012384910",
            availabilityStatus = AvailabilityStatus.ONLINE,
            verificationStatus = AccountStatus.APPROVED,
            currentLocation = "High Street Stage",
            totalDeliveries = 32,
            totalEarnings = 160000.0
        )
    )

    val sampleMiddlemen = listOf(
        Middleman(
            middlemanId = "m1",
            userId = "u_mid1",
            fullName = "Mbarara Market Connect",
            phone = "+256 750 112233",
            serviceArea = "Mbarara City & Central Market",
            description = "Direct liaison connecting customers with top market vendors.",
            commissionRate = 0.05,
            verificationStatus = AccountStatus.APPROVED,
            availabilityStatus = AvailabilityStatus.ONLINE,
            totalTransactions = 28,
            totalCommission = 45000.0
        )
    )

    val sampleOrders = listOf(
        Order(
            id = "ORD-UG-1001",
            customerId = "u_cust1",
            customerName = "Grace Atuhaire",
            customerPhone = "+256 772 123456",
            deliveryAddress = "Kikoni, Mbarara City (Near University Gate)",
            vendorId = "v1",
            vendorName = "Mbarara Fresh Groceries",
            middlemanId = "m1",
            middlemanName = "Mbarara Market Connect",
            riderId = "r1",
            riderName = "John Boda Services",
            itemsSummary = "2x Tomatoes (6,000 UGX), 1x Matooke (25,000 UGX)",
            subtotal = 31000.0,
            deliveryFee = 5000.0,
            serviceFee = 1000.0,
            middlemanFee = 1550.0,
            commissionAmount = 1550.0,
            vendorPayout = 29450.0,
            totalAmount = 38550.0,
            paymentMethod = PaymentMethod.MOBILE_MONEY,
            paymentStatus = PaymentStatus.COMPLETED,
            orderStatus = OrderStatus.PENDING,
            deliveryStatus = DeliveryStatus.ASSIGNED,
            deliveryInstructions = "Please call when near the gate.",
            timestamp = System.currentTimeMillis() - 1200000
        )
    )

    val sampleDeliveryRequests = listOf(
        DeliveryRequest(
            deliveryRequestId = "DR-501",
            orderId = "ORD-UG-1001",
            riderId = "r1",
            vendorId = "v1",
            vendorName = "Mbarara Fresh Groceries",
            customerId = "u_cust1",
            customerName = "Grace Atuhaire",
            pickupLocation = "Mbarara Central Market Stall A12",
            deliveryLocation = "Kikoni, Mbarara City",
            deliveryFee = 5000.0,
            distance = "2.2 km",
            orderValue = 38550.0,
            requestStatus = DeliveryStatus.ASSIGNED
        )
    )

    val sampleCustomerRequests = listOf(
        CustomerRequest(
            requestId = "CR-201",
            customerId = "u_cust1",
            customerName = "Grace Atuhaire",
            customerPhone = "+256 772 123456",
            requestedProducts = "1 Bunch Matooke, 2kg Yellow Beans, 1 Tray Eggs",
            preferredCategory = "Grocery Assortment",
            deliveryLocation = "Kikoni, Mbarara City",
            middlemanId = "m1",
            status = RequestStatus.PENDING
        )
    )

    val sampleNotifications = listOf(
        NotificationItem(
            notificationId = "n1",
            userId = "u_cust1",
            title = "Order Placed Successfully",
            message = "Your order ORD-UG-1001 has been sent to Mbarara Fresh Groceries.",
            channel = "SMS & EMAIL",
            relatedOrderId = "ORD-UG-1001"
        )
    )

    val sampleDisputes = listOf(
        Dispute(
            disputeId = "dsp_1",
            orderId = "ORD-UG-1001",
            reportedByUserId = "u_cust1",
            reporterName = "Grace Atuhaire",
            reporterRole = UserRole.CUSTOMER,
            title = "Delayed Delivery",
            description = "Order took longer than expected due to heavy rain in Mbarara.",
            status = DisputeStatus.OPEN
        )
    )
}
