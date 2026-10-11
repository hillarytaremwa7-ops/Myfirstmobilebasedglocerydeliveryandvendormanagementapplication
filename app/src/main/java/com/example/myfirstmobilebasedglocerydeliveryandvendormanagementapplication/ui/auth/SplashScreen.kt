package com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.components.DeveloperFooter
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onNavigateToNext: () -> Unit) {
    LaunchedEffect(Unit) {
        delay(2000)
        onNavigateToNext()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.ShoppingCart,
                contentDescription = "App Logo",
                tint = Color.White,
                modifier = Modifier.size(96.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Mbarara Central Market",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Grocery Delivery & Vendor Platform",
                color = Color.White.copy(alpha = 0.8f),
                fontSize = 14.sp
            )
        }

        DeveloperFooter(
            color = Color.White.copy(alpha = 0.8f),
            modifier = Modifier.align(Alignment.BottomEnd)
        )
    }
}
