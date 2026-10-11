package com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.Rider
import kotlinx.coroutines.flow.Flow

@Dao
interface RiderDao {
    @Query("SELECT * FROM riders")
    fun getAllRiders(): Flow<List<Rider>>

    @Query("SELECT * FROM riders WHERE riderId = :riderId")
    suspend fun getRiderById(riderId: String): Rider?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRiders(riders: List<Rider>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRider(rider: Rider)

    @Update
    suspend fun updateRider(rider: Rider)
}
