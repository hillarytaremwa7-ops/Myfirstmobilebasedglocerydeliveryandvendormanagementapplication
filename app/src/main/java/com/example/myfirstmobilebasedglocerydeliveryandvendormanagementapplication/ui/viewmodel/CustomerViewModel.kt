package com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.CustomerRequest
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.NotificationItem
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.Order
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.OrderStatus
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.PaymentMethod
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.PaymentStatus
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.Product
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.RequestStatus
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.Vendor
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.repository.GroceryRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.UUID

class CustomerViewModel(private val repository: GroceryRepository) : ViewModel() {

    val allProducts: StateFlow<List<Product>> = repository.getAllProducts()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val allVendors: StateFlow<List<Vendor>> = repository.getAllVendors()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val allOrders: StateFlow<List<Order>> = repository.getAllOrders()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val cartItems = repository.cartItems

    fun addToCart(product: Product, quantity: Int = 1) {
        repository.addToCart(product, quantity)
    }

    fun updateCartQuantity(productId: String, newQuantity: Int) {
        repository.updateCartQuantity(productId, newQuantity)
    }

    fun removeFromCart(productId: String) {
        repository.removeFromCart(productId)
    }

    fun placeOrder(
        customerName: String,
        customerPhone: String,
        deliveryAddress: String,
        paymentMethod: PaymentMethod,
        instructions: String,
        onSuccess: (Order) -> Unit
    ) {
        viewModelScope.launch {
            val items = cartItems.value
            if (items.isEmpty()) return@launch

            val subtotal = items.sumOf { it.product.price * it.quantity }
            val deliveryFee = 5000.0 // 5,000 UGX
            val serviceFee = 1000.0 // 1,000 UGX
            val totalAmount = subtotal + deliveryFee + serviceFee

            val firstVendorId = items.firstOrNull()?.product?.vendorId ?: "v1"
            val firstVendorName = items.firstOrNull()?.product?.vendorName ?: "Mbarara Fresh Groceries"
            val summary = items.joinToString(", ") { "${it.quantity}x ${it.product.name}" }

            val pStatus = if (paymentMethod == PaymentMethod.CASH_ON_DELIVERY) {
                PaymentStatus.PENDING
            } else {
                PaymentStatus.COMPLETED
            }

            val orderId = "ORD-UG-" + UUID.randomUUID().toString().take(6).uppercase()

            val newOrder = Order(
                id = orderId,
                customerId = repository.currentUser.value?.userId ?: "cust_1",
                customerName = customerName,
                customerPhone = customerPhone,
                deliveryAddress = deliveryAddress,
                vendorId = firstVendorId,
                vendorName = firstVendorName,
                itemsSummary = summary,
                subtotal = subtotal,
                deliveryFee = deliveryFee,
                serviceFee = serviceFee,
                commissionAmount = subtotal * 0.05,
                vendorPayout = subtotal * 0.95,
                totalAmount = totalAmount,
                paymentMethod = paymentMethod,
                paymentStatus = pStatus,
                orderStatus = OrderStatus.PENDING,
                deliveryInstructions = instructions
            )

            // Insert Order
            repository.insertOrder(newOrder)

            // Notify Middleman by creating CustomerRequest & Notification
            val customerReq = CustomerRequest(
                requestId = "CR-" + UUID.randomUUID().toString().take(6).uppercase(),
                customerId = newOrder.customerId,
                customerName = customerName,
                customerPhone = customerPhone,
                requestedProducts = summary,
                preferredCategory = items.firstOrNull()?.product?.category ?: "Vegetables",
                deliveryLocation = deliveryAddress,
                middlemanId = "m1",
                status = RequestStatus.PENDING
            )
            repository.insertCustomerRequest(customerReq)

            val middlemanNotify = NotificationItem(
                notificationId = "notif_" + UUID.randomUUID().toString().take(6),
                userId = "u_mid1",
                title = "New Customer Order Notification",
                message = "New order $orderId placed by $customerName ($customerPhone) for $summary. Location: $deliveryAddress.",
                relatedOrderId = orderId
            )
            repository.sendNotification(middlemanNotify)

            repository.clearCart()
            onSuccess(newOrder)
        }
    }
}
