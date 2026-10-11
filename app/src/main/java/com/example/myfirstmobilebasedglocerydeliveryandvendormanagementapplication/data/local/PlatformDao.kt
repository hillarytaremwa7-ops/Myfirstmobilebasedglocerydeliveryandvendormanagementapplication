package com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.PlatformConfig
import kotlinx.coroutines.flow.Flow

@Dao
interface PlatformDao {
    @Query("SELECT * FROM platform_config WHERE id = 1")
    fun getPlatformConfig(): Flow<PlatformConfig?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertConfig(config: PlatformConfig)

    @Update
    suspend fun updateConfig(config: PlatformConfig)
}
