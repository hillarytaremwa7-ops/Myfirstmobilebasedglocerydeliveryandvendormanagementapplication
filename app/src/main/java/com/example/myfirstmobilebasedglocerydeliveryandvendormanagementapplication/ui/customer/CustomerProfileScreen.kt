package com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.customer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.viewmodel.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomerProfileScreen(
    authViewModel: AuthViewModel,
    onBack: () -> Unit,
    onLogout: () -> Unit
) {
    val currentUser by authViewModel.currentUser.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Profile & Settings") },
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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text("Full Name: ${currentUser?.fullName ?: "Grace Atuhaire"}", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text("Email: ${currentUser?.email ?: "grace@example.com"}", fontSize = 14.sp)
                            Text("Phone: ${currentUser?.phone ?: "+256 772 123456"}", fontSize = 14.sp)
                            Text("Role: ${currentUser?.role?.label ?: "Customer"}", fontSize = 14.sp, color = MaterialTheme.colorScheme.primary)
                            Text("Default Address: ${currentUser?.address ?: "Kikoni, Mbarara City"}", fontSize = 14.sp)
                            HorizontalDivider(modifier = Modifier.padding(vertical = 10.dp))
                            Text("Notification Preferences:", fontWeight = FontWeight.Bold, fontSize = 13.sp)
                            Text("• SMS Alerts: ${if (currentUser?.enableSmsNotifications != false) "Enabled (+256...)" else "Disabled"}", fontSize = 13.sp)
                            Text("• Email Alerts: ${if (currentUser?.enableEmailNotifications != false) "Enabled (Email Sent)" else "Disabled"}", fontSize = 13.sp)
                        }
                    }
                }

                Button(
                    onClick = {
                        authViewModel.logout()
                        onLogout()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                    modifier = Modifier.fillMaxWidth().height(50.dp)
                ) {
                    Text("Log Out", color = Color.White, fontSize = 16.sp)
                }
            }

            DeveloperFooter(modifier = Modifier.align(Alignment.BottomEnd))
        }
    }
}
