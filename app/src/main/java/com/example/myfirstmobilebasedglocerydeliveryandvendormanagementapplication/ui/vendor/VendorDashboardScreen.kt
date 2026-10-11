package com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.vendor

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.OrderStatus
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.components.DeveloperFooter
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.components.ProductImageView
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.components.StatusBadge
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.components.formatUgx
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.theme.VendorOrange
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.theme.VendorOrangeContainer
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.viewmodel.VendorViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VendorDashboardScreen(
    vendorViewModel: VendorViewModel,
    onAddProduct: () -> Unit,
    onLogout: () -> Unit
) {
    var selectedTab by remember { mutableIntStateOf(0) }
    val products by vendorViewModel.vendorProducts.collectAsState()
    val orders by vendorViewModel.vendorOrders.collectAsState()

    val totalProducts = products.size
    val pendingOrders = orders.count { it.orderStatus == OrderStatus.PENDING }
    val completedOrders = orders.count { it.orderStatus == OrderStatus.DELIVERED }
    val totalSales = orders.filter { it.orderStatus == OrderStatus.DELIVERED }.sumOf { it.vendorPayout }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Vendor Dashboard • Mbarara", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = VendorOrange,
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
            NavigationBar(containerColor = VendorOrangeContainer) {
                NavigationBarItem(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    icon = { Text("Dashboard", fontWeight = FontWeight.Bold) },
                    label = { Text("Overview") }
                )
                NavigationBarItem(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    icon = { Text("Products", fontWeight = FontWeight.Bold) },
                    label = { Text("Catalog") }
                )
                NavigationBarItem(
                    selected = selectedTab == 2,
                    onClick = { selectedTab = 2 },
                    icon = { Text("Orders", fontWeight = FontWeight.Bold) },
                    label = { Text("Manage") }
                )
            }
        },
        floatingActionButton = {
            if (selectedTab == 1) {
                FloatingActionButton(
                    onClick = onAddProduct,
                    containerColor = VendorOrange,
                    contentColor = Color.White
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Add Product")
                }
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
                        Text("Vendor Performance Summary", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(12.dp))

                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                            KpiCard(title = "Total Products", value = "$totalProducts", modifier = Modifier.weight(1f))
                            KpiCard(title = "Pending Orders", value = "$pendingOrders", modifier = Modifier.weight(1f))
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                            KpiCard(title = "Completed Sales", value = "$completedOrders", modifier = Modifier.weight(1f))
                            KpiCard(title = "Vendor Earnings", value = totalSales.formatUgx(), modifier = Modifier.weight(1f))
                        }

                        Spacer(modifier = Modifier.height(20.dp))
                        Text("Recent Pending Orders", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(8.dp))

                        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            items(orders.filter { it.orderStatus == OrderStatus.PENDING }) { order ->
                                Card(
                                    modifier = Modifier.fillMaxWidth(),
                                    colors = CardDefaults.cardColors(containerColor = Color.White)
                                ) {
                                    Column(modifier = Modifier.padding(12.dp)) {
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Text(order.id, fontWeight = FontWeight.Bold)
                                            Row(verticalAlignment = Alignment.CenterVertically) {
                                                StatusBadge(text = order.orderStatus.label)
                                                Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, tint = Color.Gray, modifier = Modifier.padding(start = 4.dp))
                                            }
                                        }
                                        Text("Customer: ${order.customerName} (${order.customerPhone})", fontSize = 12.sp)
                                        Text("Items: ${order.itemsSummary}", fontSize = 12.sp, color = Color.DarkGray)
                                        Spacer(modifier = Modifier.height(6.dp))
                                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                            Button(
                                                onClick = { vendorViewModel.updateOrderStatus(order, OrderStatus.ACCEPTED) },
                                                modifier = Modifier.height(36.dp)
                                            ) {
                                                Text("Accept Order", fontSize = 12.sp)
                                                Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, modifier = Modifier.padding(start = 4.dp))
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    1 -> {
                        Text("Grocery Product Inventory", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(10.dp))

                        LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                            items(products) { product ->
                                Card(
                                    modifier = Modifier.fillMaxWidth(),
                                    colors = CardDefaults.cardColors(containerColor = Color.White)
                                ) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth().padding(12.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        ProductImageView(
                                            productName = product.name,
                                            imageUrl = product.imageUrl,
                                            category = product.category,
                                            modifier = Modifier.size(50.dp)
                                        )
                                        Spacer(modifier = Modifier.width(10.dp))

                                        Column(modifier = Modifier.weight(1f)) {
                                            Text(product.name, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                                            Text("${product.price.formatUgx()} / ${product.unit}", fontSize = 12.sp, color = VendorOrange)
                                            Text("Category: ${product.category} • Stock: ${product.stockQuantity}", fontSize = 11.sp, color = Color.Gray)
                                        }
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Switch(
                                                checked = product.isAvailable,
                                                onCheckedChange = { vendorViewModel.toggleProductAvailability(product) }
                                            )
                                            IconButton(onClick = { vendorViewModel.deleteProduct(product) }) {
                                                Icon(Icons.Default.Delete, contentDescription = "Delete", tint = Color.Red)
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    2 -> {
                        Text("Vendor Orders Management", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(10.dp))

                        LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                            items(orders) { order ->
                                Card(
                                    modifier = Modifier.fillMaxWidth(),
                                    colors = CardDefaults.cardColors(containerColor = Color.White)
                                ) {
                                    Column(modifier = Modifier.padding(12.dp)) {
                                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                            Text(order.id, fontWeight = FontWeight.Bold)
                                            StatusBadge(text = order.orderStatus.label)
                                        }
                                        Text("Customer: ${order.customerName}", fontSize = 12.sp)
                                        Text("Address: ${order.deliveryAddress}", fontSize = 12.sp, color = Color.Gray)
                                        Text("Payout: ${order.vendorPayout.formatUgx()}", fontWeight = FontWeight.Bold, color = VendorOrange)
                                        Spacer(modifier = Modifier.height(8.dp))
                                        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                                            OutlinedButton(
                                                onClick = { vendorViewModel.updateOrderStatus(order, OrderStatus.PREPARING) }
                                            ) {
                                                Text("Preparing", fontSize = 11.sp)
                                                Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, modifier = Modifier.padding(start = 2.dp))
                                            }
                                            OutlinedButton(
                                                onClick = { vendorViewModel.updateOrderStatus(order, OrderStatus.READY_FOR_DELIVERY) }
                                            ) {
                                                Text("Ready for Boda", fontSize = 11.sp)
                                                Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, modifier = Modifier.padding(start = 2.dp))
                                            }
                                        }
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
fun KpiCard(title: String, value: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = VendorOrangeContainer)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(title, fontSize = 11.sp, color = VendorOrange)
            Spacer(modifier = Modifier.height(4.dp))
            Text(value, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = VendorOrange)
        }
    }
}
