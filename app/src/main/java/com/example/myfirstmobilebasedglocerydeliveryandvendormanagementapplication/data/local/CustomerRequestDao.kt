package com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.CustomerRequest
import kotlinx.coroutines.flow.Flow

@Dao
interface CustomerRequestDao {
    @Query("SELECT * FROM customer_requests ORDER BY createdAt DESC")
    fun getAllCustomerRequests(): Flow<List<CustomerRequest>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCustomerRequests(requests: List<CustomerRequest>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCustomerRequest(request: CustomerRequest)

    @Update
    suspend fun updateCustomerRequest(request: CustomerRequest)
}
