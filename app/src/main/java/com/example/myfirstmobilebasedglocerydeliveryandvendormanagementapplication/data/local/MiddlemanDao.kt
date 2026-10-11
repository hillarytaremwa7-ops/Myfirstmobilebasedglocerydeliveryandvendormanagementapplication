package com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.Middleman
import kotlinx.coroutines.flow.Flow

@Dao
interface MiddlemanDao {
    @Query("SELECT * FROM middlemen")
    fun getAllMiddlemen(): Flow<List<Middleman>>

    @Query("SELECT * FROM middlemen WHERE middlemanId = :middlemanId")
    suspend fun getMiddlemanById(middlemanId: String): Middleman?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMiddlemen(middlemen: List<Middleman>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMiddleman(middleman: Middleman)

    @Update
    suspend fun updateMiddleman(middleman: Middleman)
}
