package com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.Dispute
import kotlinx.coroutines.flow.Flow

@Dao
interface DisputeDao {
    @Query("SELECT * FROM disputes ORDER BY createdAt DESC")
    fun getAllDisputes(): Flow<List<Dispute>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDispute(dispute: Dispute)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDisputes(disputes: List<Dispute>)

    @Update
    suspend fun updateDispute(dispute: Dispute)
}
