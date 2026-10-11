package com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.customer

import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.Order
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.components.DeveloperFooter
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.components.StatusBadge
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.components.formatUgx

@Composable
fun OrderConfirmationScreen(
    order: Order,
    onDone: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Check, contentDescription = null, tint = Color.White, modifier = Modifier.size(48.dp))
            }
            Spacer(modifier = Modifier.height(20.dp))
            Text("Order Placed Successfully!", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
            Text("Your order has been sent to Mbarara Central Market vendor.", fontSize = 14.sp, color = Color.Gray, textAlign = TextAlign.Center)

            Spacer(modifier = Modifier.height(20.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Order ID: ${order.id}", fontWeight = FontWeight.Bold)
                        StatusBadge(text = order.orderStatus.name)
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Payment Status:", fontSize = 13.sp)
                        StatusBadge(text = order.paymentStatus.name)
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Text("Vendor: ${order.vendorName}", fontSize = 13.sp)
                    Text("Items: ${order.itemsSummary}", fontSize = 13.sp)
                    Text("Total Amount: ${order.totalAmount.formatUgx()}", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                    Text("Delivery Address: ${order.deliveryAddress}", fontSize = 13.sp)
                    Text("Payment Method: ${order.paymentMethod.displayName}", fontSize = 13.sp)
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            Button(
                onClick = onDone,
                modifier = Modifier.fillMaxWidth().height(50.dp)
            ) {
                Text("Track My Orders", fontSize = 16.sp)
            }
        }

        DeveloperFooter(modifier = Modifier.align(Alignment.BottomEnd))
    }
}
