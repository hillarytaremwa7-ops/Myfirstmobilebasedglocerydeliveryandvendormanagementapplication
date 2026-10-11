package com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.middleman

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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.RequestStatus
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.components.DeveloperFooter
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.components.StatusBadge
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.theme.MiddlemanPurple
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.theme.MiddlemanPurpleContainer
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.viewmodel.MiddlemanViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MiddlemanDashboardScreen(
    middlemanViewModel: MiddlemanViewModel,
    onLogout: () -> Unit
) {
    var selectedTab by remember { mutableIntStateOf(0) }
    val requests by middlemanViewModel.customerRequests.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Middleman Broker • Mbarara", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MiddlemanPurple,
                    titleContentColor = Color.White,
                    actionIconContentColor = Color.White
                ),
                actions = {
                    IconButton(onClick = onLogout) {
                        Icon(Icons.AutoMirrored.Filled.Logout, contentDescription = "Log Out")
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar(containerColor = MiddlemanPurpleContainer) {
                NavigationBarItem(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    icon = { Text("Requests", fontWeight = FontWeight.Bold) },
                    label = { Text("Customer Needs") }
                )
                NavigationBarItem(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    icon = { Text("Commissions", fontWeight = FontWeight.Bold) },
                    label = { Text("Earnings") }
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
                        Text("Customer Grocery Coordination Requests", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(10.dp))

                        LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                            items(requests) { req ->
                                Card(
                                    modifier = Modifier.fillMaxWidth(),
                                    colors = CardDefaults.cardColors(containerColor = Color.White)
                                ) {
                                    Column(modifier = Modifier.padding(12.dp)) {
                                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                            Text(req.requestId, fontWeight = FontWeight.Bold)
                                            StatusBadge(text = req.status.label)
                                        }
                                        Text("Customer: ${req.customerName} (${req.customerPhone})", fontSize = 12.sp)
                                        Text("Needed: ${req.requestedProducts}", fontSize = 12.sp, color = Color.DarkGray)
                                        Text("Location: ${req.deliveryLocation}", fontSize = 11.sp, color = Color.Gray)
                                        Spacer(modifier = Modifier.height(8.dp))
                                        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                                            Button(
                                                onClick = { middlemanViewModel.updateRequestStatus(req, RequestStatus.ACCEPTED) }
                                            ) {
                                                Text("Accept & Source Vendor", fontSize = 11.sp)
                                                Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, modifier = Modifier.padding(start = 2.dp))
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    1 -> {
                        Text("Middleman Commission Earnings", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(12.dp))

                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(containerColor = MiddlemanPurpleContainer)
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text("Agent: Mbarara Market Connect", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                                Text("Service Area: Mbarara City & Central Market", fontSize = 12.sp)
                                Text("Standard Commission: 5%", fontSize = 12.sp)
                                Spacer(modifier = Modifier.height(8.dp))
                                Text("Total Coordinated Transactions: 28", fontSize = 14.sp)
                                Text("Total Commissions Earned: UGX 45,000", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = MiddlemanPurple)
                            }
                        }
                    }
                }
            }

            DeveloperFooter(modifier = Modifier.align(Alignment.BottomEnd))
        }
    }
}
