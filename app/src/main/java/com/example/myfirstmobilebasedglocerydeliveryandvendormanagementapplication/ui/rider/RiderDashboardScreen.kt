package com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.rider

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.AvailabilityStatus
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.DeliveryStatus
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.components.DeveloperFooter
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.components.StatusBadge
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.components.formatUgx
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.theme.RiderBlue
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.theme.RiderBlueContainer
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.viewmodel.RiderViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RiderDashboardScreen(
    riderViewModel: RiderViewModel,
    onLogout: () -> Unit
) {
    var selectedTab by remember { mutableIntStateOf(0) }
    var isOnline by remember { mutableStateOf(true) }
    val requests by riderViewModel.allDeliveryRequests.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Boda Rider Dashboard • Mbarara", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = RiderBlue,
                    titleContentColor = Color.White,
                    actionIconContentColor = Color.White
                ),
                actions = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(end = 4.dp)
                    ) {
                        Text(if (isOnline) "ONLINE" else "OFFLINE", color = Color.White, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.padding(horizontal = 2.dp))
                        Switch(
                            checked = isOnline,
                            onCheckedChange = {
                                isOnline = it
                                riderViewModel.updateRiderAvailability("r1", if (it) AvailabilityStatus.ONLINE else AvailabilityStatus.OFFLINE)
                            }
                        )
                    }
                    IconButton(onClick = onLogout) {
                        Icon(Icons.AutoMirrored.Filled.Logout, contentDescription = "Log Out")
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar(containerColor = RiderBlueContainer) {
                NavigationBarItem(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    icon = { Text("Requests", fontWeight = FontWeight.Bold) },
                    label = { Text("Deliveries") }
                )
                NavigationBarItem(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    icon = { Text("Earnings", fontWeight = FontWeight.Bold) },
                    label = { Text("Reports") }
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 28.dp)
            ) {
                when (selectedTab) {
                    0 -> {
                        Text("Available Delivery Requests", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(10.dp))

                        LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                            items(requests) { req ->
                                Card(
                                    modifier = Modifier.fillMaxWidth(),
                                    colors = CardDefaults.cardColors(containerColor = Color.White)
                                ) {
                                    Column(modifier = Modifier.padding(12.dp)) {
                                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                            Text(req.deliveryRequestId, fontWeight = FontWeight.Bold)
                                            StatusBadge(text = req.requestStatus.label)
                                        }
                                        Text("Pickup: ${req.pickupLocation} (${req.vendorName})", fontSize = 12.sp)
                                        Text("Dropoff: ${req.deliveryLocation} (${req.customerName})", fontSize = 12.sp, color = Color.DarkGray)
                                        Text("Fee: ${req.deliveryFee.formatUgx()} • Dist: ${req.distance}", fontWeight = FontWeight.Bold, color = RiderBlue)
                                        Spacer(modifier = Modifier.height(8.dp))
                                        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                                            Button(
                                                onClick = { riderViewModel.updateDeliveryRequestStatus(req, DeliveryStatus.GOING_TO_PICKUP) }
                                            ) {
                                                Text("Accept & Pickup", fontSize = 11.sp)
                                                Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, modifier = Modifier.padding(start = 2.dp))
                                            }
                                            OutlinedButton(
                                                onClick = { riderViewModel.updateDeliveryRequestStatus(req, DeliveryStatus.DELIVERED) }
                                            ) {
                                                Text("Confirm Delivery", fontSize = 11.sp)
                                                Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, modifier = Modifier.padding(start = 2.dp))
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    1 -> {
                        Text("Rider Earnings Summary", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(12.dp))

                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(containerColor = RiderBlueContainer)
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text("Rider: John Boda Services", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                                Text("Motorcycle: UFE 432B • License: DL-882319", fontSize = 12.sp)
                                Spacer(modifier = Modifier.height(8.dp))
                                Text("Total Deliveries: 48", fontSize = 14.sp)
                                Text("Total Boda Earnings: UGX 240,000", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = RiderBlue)
                            }
                        }
                    }
                }
            }

            DeveloperFooter(modifier = Modifier.align(Alignment.BottomEnd))
        }
    }
}
