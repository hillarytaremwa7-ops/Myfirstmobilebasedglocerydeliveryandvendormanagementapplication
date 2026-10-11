package com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.CustomerRequest
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.DeliveryRequest
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.Dispute
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.Middleman
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.NotificationItem
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.Order
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.PlatformConfig
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.Product
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.Review
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.Rider
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.User
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.Vendor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [
        User::class,
        Vendor::class,
        Product::class,
        Order::class,
        Rider::class,
        Middleman::class,
        DeliveryRequest::class,
        CustomerRequest::class,
        NotificationItem::class,
        Dispute::class,
        Review::class,
        PlatformConfig::class
    ],
    version = 3,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class GroceryDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun vendorDao(): VendorDao
    abstract fun productDao(): ProductDao
    abstract fun orderDao(): OrderDao
    abstract fun riderDao(): RiderDao
    abstract fun middlemanDao(): MiddlemanDao
    abstract fun deliveryRequestDao(): DeliveryRequestDao
    abstract fun customerRequestDao(): CustomerRequestDao
    abstract fun notificationDao(): NotificationDao
    abstract fun disputeDao(): DisputeDao
    abstract fun reviewDao(): ReviewDao
    abstract fun platformDao(): PlatformDao

    companion object {
        @Volatile
        private var INSTANCE: GroceryDatabase? = null

        fun getDatabase(context: Context): GroceryDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GroceryDatabase::class.java,
                    "mbarara_grocery_delivery_db"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(DatabaseCallback())
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private class DatabaseCallback : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    CoroutineScope(Dispatchers.IO).launch {
                        database.platformDao().insertConfig(SampleData.defaultConfig)
                        database.userDao().insertUsers(SampleData.sampleUsers)
                        database.vendorDao().insertVendors(SampleData.sampleVendors)
                        database.productDao().insertProducts(SampleData.sampleProducts)
                        database.orderDao().insertOrders(SampleData.sampleOrders)
                        database.riderDao().insertRiders(SampleData.sampleRiders)
                        database.middlemanDao().insertMiddlemen(SampleData.sampleMiddlemen)
                        database.deliveryRequestDao().insertDeliveryRequests(SampleData.sampleDeliveryRequests)
                        database.customerRequestDao().insertCustomerRequests(SampleData.sampleCustomerRequests)
                        database.notificationDao().insertNotifications(SampleData.sampleNotifications)
                        database.disputeDao().insertDisputes(SampleData.sampleDisputes)
                    }
                }
            }
        }
    }
}
