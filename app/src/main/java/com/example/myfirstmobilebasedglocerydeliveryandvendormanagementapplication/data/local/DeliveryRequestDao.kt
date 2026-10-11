package com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.DeliveryRequest
import kotlinx.coroutines.flow.Flow

@Dao
interface DeliveryRequestDao {
    @Query("SELECT * FROM delivery_requests ORDER BY assignedAt DESC")
    fun getAllDeliveryRequests(): Flow<List<DeliveryRequest>>

    @Query("SELECT * FROM delivery_requests WHERE riderId = :riderId ORDER BY assignedAt DESC")
    fun getRequestsByRider(riderId: String): Flow<List<DeliveryRequest>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDeliveryRequests(requests: List<DeliveryRequest>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDeliveryRequest(request: DeliveryRequest)

    @Update
    suspend fun updateDeliveryRequest(request: DeliveryRequest)
}
