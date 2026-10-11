package com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.repository.GroceryRepository

class ViewModelFactory(private val repository: GroceryRepository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> AuthViewModel(repository) as T
            modelClass.isAssignableFrom(CustomerViewModel::class.java) -> CustomerViewModel(repository) as T
            modelClass.isAssignableFrom(VendorViewModel::class.java) -> VendorViewModel(repository) as T
            modelClass.isAssignableFrom(RiderViewModel::class.java) -> RiderViewModel(repository) as T
            modelClass.isAssignableFrom(MiddlemanViewModel::class.java) -> MiddlemanViewModel(repository) as T
            modelClass.isAssignableFrom(AdminViewModel::class.java) -> AdminViewModel(repository) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}
