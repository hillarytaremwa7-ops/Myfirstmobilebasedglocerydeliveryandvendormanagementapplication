package com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.Vendor
import kotlinx.coroutines.flow.Flow

@Dao
interface VendorDao {
    @Query("SELECT * FROM vendors")
    fun getAllVendors(): Flow<List<Vendor>>

    @Query("SELECT * FROM vendors WHERE id = :id")
    suspend fun getVendorById(id: String): Vendor?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVendors(vendors: List<Vendor>)

    @Update
    suspend fun updateVendor(vendor: Vendor)
}
