package com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.Review
import kotlinx.coroutines.flow.Flow

@Dao
interface ReviewDao {
    @Query("SELECT * FROM reviews WHERE vendorId = :vendorId ORDER BY createdAt DESC")
    fun getReviewsByVendor(vendorId: String): Flow<List<Review>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReview(review: Review)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReviews(reviews: List<Review>)
}
