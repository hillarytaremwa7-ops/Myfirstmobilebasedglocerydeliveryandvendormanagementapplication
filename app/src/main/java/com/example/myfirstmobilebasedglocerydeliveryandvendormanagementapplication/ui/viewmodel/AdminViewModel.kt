package com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.AccountStatus
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.Dispute
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.Middleman
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.Order
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.Product
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.Rider
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.User
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.Vendor
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.repository.GroceryRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AdminViewModel(private val repository: GroceryRepository) : ViewModel() {

    val allUsers: StateFlow<List<User>> = repository.getAllUsers()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val allVendors: StateFlow<List<Vendor>> = repository.getAllVendors()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val allRiders: StateFlow<List<Rider>> = repository.getAllRiders()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val allMiddlemen: StateFlow<List<Middleman>> = repository.getAllMiddlemen()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val allProducts: StateFlow<List<Product>> = repository.getAllProducts()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val allOrders: StateFlow<List<Order>> = repository.getAllOrders()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val allDisputes: StateFlow<List<Dispute>> = repository.getAllDisputes()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun updateUserStatus(userId: String, status: AccountStatus) {
        viewModelScope.launch {
            repository.updateUserStatus(userId, status)
        }
    }

    fun updateVendorStatus(vendorId: String, status: AccountStatus) {
        viewModelScope.launch {
            repository.updateVendorStatus(vendorId, status)
        }
    }

    fun updateRiderVerification(riderId: String, status: AccountStatus) {
        viewModelScope.launch {
            repository.updateRiderVerification(riderId, status)
        }
    }
}
