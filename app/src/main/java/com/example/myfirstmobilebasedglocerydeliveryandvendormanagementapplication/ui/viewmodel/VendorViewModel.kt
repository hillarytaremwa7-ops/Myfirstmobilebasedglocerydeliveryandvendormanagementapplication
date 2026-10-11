package com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.Order
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.OrderStatus
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.Product
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.repository.GroceryRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.UUID

import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.util.ProductImageHelper

class VendorViewModel(private val repository: GroceryRepository) : ViewModel() {

    val vendorProducts: StateFlow<List<Product>> = repository.getAllProducts()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val vendorOrders: StateFlow<List<Order>> = repository.getAllOrders()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun addProduct(
        name: String,
        category: String,
        price: Double,
        unit: String,
        description: String,
        stockQuantity: Int,
        imageUrl: String = "",
        vendorId: String = "v1"
    ) {
        viewModelScope.launch {
            val realImageUrl = ProductImageHelper.getRealImageUrl(name, category, imageUrl)
            val currentU = repository.currentUser.value
            val vName = if (currentU != null && currentU.fullName.isNotEmpty()) currentU.fullName else "Mbarara Fresh Groceries"

            val newProduct = Product(
                id = "p_" + UUID.randomUUID().toString().take(6),
                vendorId = currentU?.userId ?: vendorId,
                vendorName = vName,
                name = name,
                category = category,
                unit = unit,
                price = price,
                description = description,
                stockQuantity = stockQuantity,
                imageUrl = realImageUrl,
                iconName = "vegetables",
                isAvailable = true
            )
            repository.insertProduct(newProduct)
        }
    }

    fun updateProduct(product: Product) {
        viewModelScope.launch {
            repository.updateProduct(product)
        }
    }

    fun toggleProductAvailability(product: Product) {
        viewModelScope.launch {
            repository.updateProduct(product.copy(isAvailable = !product.isAvailable))
        }
    }

    fun deleteProduct(product: Product) {
        viewModelScope.launch {
            repository.deleteProduct(product)
        }
    }

    fun updateOrderStatus(order: Order, newStatus: OrderStatus) {
        viewModelScope.launch {
            repository.insertOrder(order.copy(orderStatus = newStatus))
        }
    }
}
