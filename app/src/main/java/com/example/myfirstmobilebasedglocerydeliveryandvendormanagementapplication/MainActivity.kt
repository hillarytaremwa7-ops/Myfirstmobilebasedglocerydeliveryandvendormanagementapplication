package com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.local.GroceryDatabase
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.repository.GroceryRepository
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.navigation.GroceryNavGraph
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.theme.GroceryTheme
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.viewmodel.AdminViewModel
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.viewmodel.AuthViewModel
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.viewmodel.CustomerViewModel
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.viewmodel.MiddlemanViewModel
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.viewmodel.RiderViewModel
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.viewmodel.VendorViewModel
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.viewmodel.ViewModelFactory

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val database = GroceryDatabase.getDatabase(applicationContext)
        val repository = GroceryRepository(database)
        val factory = ViewModelFactory(repository)

        val authViewModel: AuthViewModel by viewModels { factory }
        val customerViewModel: CustomerViewModel by viewModels { factory }
        val vendorViewModel: VendorViewModel by viewModels { factory }
        val riderViewModel: RiderViewModel by viewModels { factory }
        val middlemanViewModel: MiddlemanViewModel by viewModels { factory }
        val adminViewModel: AdminViewModel by viewModels { factory }

        setContent {
            GroceryTheme {
                GroceryNavGraph(
                    authViewModel = authViewModel,
                    customerViewModel = customerViewModel,
                    vendorViewModel = vendorViewModel,
                    riderViewModel = riderViewModel,
                    middlemanViewModel = middlemanViewModel,
                    adminViewModel = adminViewModel
                )
            }
        }
    }
}
