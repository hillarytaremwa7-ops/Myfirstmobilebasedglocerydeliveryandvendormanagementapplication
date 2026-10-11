package com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.admin

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
import androidx.compose.material3.ButtonDefaults
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
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.AccountStatus
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.components.DeveloperFooter
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.components.StatusBadge
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.components.formatUgx
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.theme.AdminRed
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.theme.AdminRedContainer
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.viewmodel.AdminViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminDashboardScreen(
    adminViewModel: AdminViewModel,
    onLogout: () -> Unit
) {
    var selectedTab by remember { mutableIntStateOf(0) }
    val users by adminViewModel.allUsers.collectAsState()
    val vendors by adminViewModel.allVendors.collectAsState()
    val riders by adminViewModel.allRiders.collectAsState()
    val middlemen by adminViewModel.allMiddlemen.collectAsState()
    val products by adminViewModel.allProducts.collectAsState()
    val orders by adminViewModel.allOrders.collectAsState()
    val disputes by adminViewModel.allDisputes.collectAsState()

    val totalSales = orders.sumOf { it.totalAmount }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Market Admin Console • Mbarara", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = AdminRed,
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
            NavigationBar(containerColor = AdminRedContainer) {
                NavigationBarItem(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    icon = { Text("Overview", fontWeight = FontWeight.Bold) },
                    label = { Text("KPIs") }
                )
                NavigationBarItem(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    icon = { Text("Users", fontWeight = FontWeight.Bold) },
                    label = { Text("Accounts") }
                )
                NavigationBarItem(
                    selected = selectedTab == 2,
                    onClick = { selectedTab = 2 },
                    icon = { Text("Disputes", fontWeight = FontWeight.Bold) },
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
                        Text("Platform Statistics Overview", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(10.dp))

                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            AdminKpiCard(title = "Registered Users", value = "${users.size}", modifier = Modifier.weight(1f))
                            AdminKpiCard(title = "Active Vendors", value = "${vendors.size}", modifier = Modifier.weight(1f))
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            AdminKpiCard(title = "Boda Riders", value = "${riders.size}", modifier = Modifier.weight(1f))
                            AdminKpiCard(title = "Middlemen", value = "${middlemen.size}", modifier = Modifier.weight(1f))
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            AdminKpiCard(title = "Total Products", value = "${products.size}", modifier = Modifier.weight(1f))
                            AdminKpiCard(title = "Total Orders", value = "${orders.size}", modifier = Modifier.weight(1f))
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        AdminKpiCard(title = "Total Market Gross Revenue", value = totalSales.formatUgx(), modifier = Modifier.fillMaxWidth())
                    }
                    1 -> {
                        Text("User & Partner Account Approvals", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(10.dp))

                        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            items(users) { user ->
                                Card(
                                    modifier = Modifier.fillMaxWidth(),
                                    colors = CardDefaults.cardColors(containerColor = Color.White)
                                ) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth().padding(12.dp),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Column(modifier = Modifier.weight(1f)) {
                                            Text(user.fullName, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                                            Text("${user.role.label} • ${user.phone}", fontSize = 12.sp, color = Color.Gray)
                                            Text("Email: ${user.email}", fontSize = 11.sp, color = Color.Gray)
                                        }
                                        Column {
                                            StatusBadge(text = user.accountStatus.name)
                                            Spacer(modifier = Modifier.height(4.dp))
                                            if (user.accountStatus == AccountStatus.PENDING) {
                                                Button(
                                                    onClick = { adminViewModel.updateUserStatus(user.userId, AccountStatus.APPROVED) },
                                                    colors = ButtonDefaults.buttonColors(containerColor = AdminRed)
                                                ) {
                                                    Text("Approve", fontSize = 10.sp)
                                                    Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, modifier = Modifier.padding(start = 2.dp))
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    2 -> {
                        Text("Disputes & Reported Issues", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(10.dp))

                        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            items(disputes) { dsp ->
                                Card(
                                    modifier = Modifier.fillMaxWidth(),
                                    colors = CardDefaults.cardColors(containerColor = Color.White)
                                ) {
                                    Column(modifier = Modifier.padding(12.dp)) {
                                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                            Text(dsp.title, fontWeight = FontWeight.Bold)
                                            StatusBadge(text = dsp.status.name)
                                        }
                                        Text("Reported by: ${dsp.reporterName} (${dsp.reporterRole.label})", fontSize = 12.sp)
                                        Text("Description: ${dsp.description}", fontSize = 12.sp, color = Color.DarkGray)
                                    }
                                }
                            }
                        }
                    }
                }
            }

            DeveloperFooter(modifier = Modifier.align(Alignment.BottomEnd))
        }
    }
}

@Composable
fun AdminKpiCard(title: String, value: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = AdminRedContainer)
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Text(title, fontSize = 11.sp, color = AdminRed)
            Spacer(modifier = Modifier.height(2.dp))
            Text(value, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = AdminRed)
        }
    }
}
