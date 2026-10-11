package com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.customer

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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.components.DeveloperFooter
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.components.ProductImageView
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.components.formatUgx
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.viewmodel.CustomerViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    customerViewModel: CustomerViewModel,
    onBack: () -> Unit,
    onProceedToCheckout: () -> Unit
) {
    val cartItems by customerViewModel.cartItems.collectAsState()

    val subtotal = cartItems.sumOf { it.product.price * it.quantity }
    val deliveryFee = if (cartItems.isNotEmpty()) 5000.0 else 0.0
    val serviceFee = if (cartItems.isNotEmpty()) 1000.0 else 0.0
    val totalAmount = subtotal + deliveryFee + serviceFee

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Shopping Cart") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            if (cartItems.isEmpty()) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text("Your cart is empty", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    Text("Add groceries from Mbarara vendors to get started", fontSize = 14.sp, color = Color.Gray)
                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    LazyColumn(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(cartItems) { item ->
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                colors = CardDefaults.cardColors(containerColor = Color.White),
                                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(12.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    ProductImageView(
                                        productName = item.product.name,
                                        imageUrl = item.product.imageUrl,
                                        category = item.product.category,
                                        modifier = Modifier.size(56.dp)
                                    )
                                    Spacer(modifier = Modifier.width(12.dp))

                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(item.product.name, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                                        Text("${item.product.price.formatUgx()} / ${item.product.unit}", fontSize = 12.sp, color = Color.Gray)
                                        Text(item.product.vendorName, fontSize = 11.sp, color = MaterialTheme.colorScheme.primary)
                                    }

                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        IconButton(onClick = {
                                            customerViewModel.updateCartQuantity(item.product.id, item.quantity - 1)
                                        }) {
                                            Icon(Icons.Default.Remove, contentDescription = "Decrease")
                                        }
                                        Text("${item.quantity}", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                                        IconButton(onClick = {
                                            customerViewModel.updateCartQuantity(item.product.id, item.quantity + 1)
                                        }) {
                                            Icon(Icons.Default.Add, contentDescription = "Increase")
                                        }
                                        IconButton(onClick = {
                                            customerViewModel.removeFromCart(item.product.id)
                                        }) {
                                            Icon(Icons.Default.Delete, contentDescription = "Remove", tint = Color.Red)
                                        }
                                    }
                                }
                            }
                        }
                    }

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                Text("Subtotal")
                                Text(subtotal.formatUgx(), fontWeight = FontWeight.Bold)
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                Text("Delivery Fee (Boda Rider)")
                                Text(deliveryFee.formatUgx())
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                Text("Service Fee")
                                Text(serviceFee.formatUgx())
                            }
                            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                Text("Total Amount", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                                Text(totalAmount.formatUgx(), fontWeight = FontWeight.Bold, fontSize = 18.sp, color = MaterialTheme.colorScheme.primary)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = onProceedToCheckout,
                        modifier = Modifier.fillMaxWidth().height(50.dp)
                    ) {
                        Text("Proceed to Checkout (${totalAmount.formatUgx()})", fontSize = 16.sp)
                    }
                }
            }

            DeveloperFooter(modifier = Modifier.align(Alignment.BottomEnd))
        }
    }
}
