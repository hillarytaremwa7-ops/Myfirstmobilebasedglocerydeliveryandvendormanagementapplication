package com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.AvailabilityStatus
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.DeliveryRequest
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.DeliveryStatus
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.Rider
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.repository.GroceryRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class RiderViewModel(private val repository: GroceryRepository) : ViewModel() {

    val allDeliveryRequests: StateFlow<List<DeliveryRequest>> = repository.getAllDeliveryRequests()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val allRiders: StateFlow<List<Rider>> = repository.getAllRiders()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun updateRiderAvailability(riderId: String, status: AvailabilityStatus) {
        viewModelScope.launch {
            repository.updateRiderAvailability(riderId, status)
        }
    }

    fun updateDeliveryRequestStatus(request: DeliveryRequest, status: DeliveryStatus) {
        viewModelScope.launch {
            val updated = request.copy(requestStatus = status)
            repository.insertDeliveryRequest(updated)
        }
    }
}
