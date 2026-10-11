package com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.repository

import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.local.GroceryDatabase
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.AccountStatus
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.AvailabilityStatus
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.CartItem
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.CustomerRequest
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.DeliveryRequest
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.DeliveryStatus
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.Dispute
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.Middleman
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.NotificationItem
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.Order
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.OrderStatus
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.Product
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.RequestStatus
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.Review
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.Rider
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.User
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.UserRole
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.Vendor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.UUID

class GroceryRepository(private val db: GroceryDatabase) {

    // Current User Session
    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser.asStateFlow()

    // Shopping Cart
    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    val cartItems: StateFlow<List<CartItem>> = _cartItems.asStateFlow()

    fun setCurrentUser(user: User?) {
        _currentUser.value = user
    }

    // Users
    fun getAllUsers(): Flow<List<User>> = db.userDao().getAllUsers()
    suspend fun getUserById(userId: String) = db.userDao().getUserById(userId)
    suspend fun getUserByEmail(email: String) = db.userDao().getUserByEmail(email)
    suspend fun insertUser(user: User) = db.userDao().insertUser(user)
    suspend fun updateUserStatus(userId: String, status: AccountStatus) {
        val user = db.userDao().getUserById(userId)
        if (user != null) {
            db.userDao().updateUser(user.copy(accountStatus = status))
        }
    }

    // Vendors
    fun getAllVendors(): Flow<List<Vendor>> = db.vendorDao().getAllVendors()
    suspend fun getVendorById(id: String) = db.vendorDao().getVendorById(id)
    suspend fun insertVendor(vendor: Vendor) = db.vendorDao().insertVendors(listOf(vendor))
    suspend fun updateVendorStatus(vendorId: String, status: AccountStatus) {
        val vendor = db.vendorDao().getVendorById(vendorId)
        if (vendor != null) {
            db.vendorDao().updateVendor(vendor.copy(status = status))
        }
    }

    // Products
    fun getAllProducts(): Flow<List<Product>> = db.productDao().getAllProducts()
    fun getProductsByVendor(vendorId: String): Flow<List<Product>> = db.productDao().getProductsByVendor(vendorId)
    fun getProductsByCategory(category: String): Flow<List<Product>> = db.productDao().getProductsByCategory(category)
    suspend fun getProductById(id: String) = db.productDao().getProductById(id)
    suspend fun insertProduct(product: Product) = db.productDao().insertProduct(product)
    suspend fun updateProduct(product: Product) = db.productDao().updateProduct(product)
    suspend fun deleteProduct(product: Product) = db.productDao().deleteProduct(product)

    // Orders
    fun getAllOrders(): Flow<List<Order>> = db.orderDao().getAllOrders()
    fun getOrdersByVendor(vendorId: String): Flow<List<Order>> = db.orderDao().getOrdersByVendor(vendorId)
    suspend fun insertOrder(order: Order) = db.orderDao().insertOrder(order)
    suspend fun updateOrderStatus(orderId: String, status: OrderStatus) {
        // Implementation for updating order status
    }

    // Riders
    fun getAllRiders(): Flow<List<Rider>> = db.riderDao().getAllRiders()
    suspend fun getRiderById(riderId: String) = db.riderDao().getRiderById(riderId)
    suspend fun insertRider(rider: Rider) = db.riderDao().insertRider(rider)
    suspend fun updateRiderAvailability(riderId: String, status: AvailabilityStatus) {
        val rider = db.riderDao().getRiderById(riderId)
        if (rider != null) {
            db.riderDao().updateRider(rider.copy(availabilityStatus = status))
        }
    }
    suspend fun updateRiderVerification(riderId: String, status: AccountStatus) {
        val rider = db.riderDao().getRiderById(riderId)
        if (rider != null) {
            db.riderDao().updateRider(rider.copy(verificationStatus = status))
        }
    }

    // Middlemen
    fun getAllMiddlemen(): Flow<List<Middleman>> = db.middlemanDao().getAllMiddlemen()
    suspend fun getMiddlemanById(id: String) = db.middlemanDao().getMiddlemanById(id)
    suspend fun insertMiddleman(middleman: Middleman) = db.middlemanDao().insertMiddleman(middleman)

    // Delivery Requests
    fun getAllDeliveryRequests(): Flow<List<DeliveryRequest>> = db.deliveryRequestDao().getAllDeliveryRequests()
    fun getRequestsByRider(riderId: String): Flow<List<DeliveryRequest>> = db.deliveryRequestDao().getRequestsByRider(riderId)
    suspend fun insertDeliveryRequest(request: DeliveryRequest) = db.deliveryRequestDao().insertDeliveryRequest(request)

    // Customer Requests for Middleman
    fun getAllCustomerRequests(): Flow<List<CustomerRequest>> = db.customerRequestDao().getAllCustomerRequests()
    suspend fun insertCustomerRequest(request: CustomerRequest) = db.customerRequestDao().insertCustomerRequest(request)

    // Notifications
    fun getNotificationsByUser(userId: String): Flow<List<NotificationItem>> = db.notificationDao().getNotificationsByUser(userId)
    suspend fun sendNotification(notification: NotificationItem) = db.notificationDao().insertNotification(notification)

    // Disputes
    fun getAllDisputes(): Flow<List<Dispute>> = db.disputeDao().getAllDisputes()
    suspend fun insertDispute(dispute: Dispute) = db.disputeDao().insertDispute(dispute)

    // Reviews
    fun getReviewsByVendor(vendorId: String): Flow<List<Review>> = db.reviewDao().getReviewsByVendor(vendorId)
    suspend fun insertReview(review: Review) = db.reviewDao().insertReview(review)

    // Cart Management
    fun addToCart(product: Product, quantity: Int = 1) {
        val current = _cartItems.value.toMutableList()
        val index = current.indexOfFirst { it.product.id == product.id }
        if (index >= 0) {
            val existing = current[index]
            current[index] = existing.copy(quantity = existing.quantity + quantity)
        } else {
            current.add(CartItem(product, quantity))
        }
        _cartItems.value = current
    }

    fun updateCartQuantity(productId: String, newQuantity: Int) {
        if (newQuantity <= 0) {
            removeFromCart(productId)
            return
        }
        val current = _cartItems.value.toMutableList()
        val index = current.indexOfFirst { it.product.id == productId }
        if (index >= 0) {
            current[index] = current[index].copy(quantity = newQuantity)
            _cartItems.value = current
        }
    }

    fun removeFromCart(productId: String) {
        _cartItems.value = _cartItems.value.filter { it.product.id != productId }
    }

    fun clearCart() {
        _cartItems.value = emptyList()
    }
}
