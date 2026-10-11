package com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.auth

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.UserRole
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.components.DeveloperFooter
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.viewmodel.AuthState
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.viewmodel.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    authViewModel: AuthViewModel,
    onLoginSuccess: (UserRole) -> Unit,
    onNavigateToRegister: () -> Unit
) {
    var selectedRole by remember { mutableStateOf(UserRole.CUSTOMER) }
    var email by remember { mutableStateOf("grace@example.com") }
    var password by remember { mutableStateOf("123456") }
    var expanded by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val authState by authViewModel.authState.collectAsState()

    fun updateRoleCredentials(role: UserRole) {
        selectedRole = role
        errorMessage = null
        when (role) {
            UserRole.CUSTOMER -> {
                email = "grace@example.com"
                password = "123456"
            }
            UserRole.VENDOR -> {
                email = "mamasarah@market.co.ug"
                password = "123456"
            }
            UserRole.RIDER -> {
                email = "john.boda@mbarara.com"
                password = "123456"
            }
            UserRole.MIDDLEMAN -> {
                email = "connect@mbararamarket.ug"
                password = "123456"
            }
            UserRole.ADMIN -> {
                email = "htaremwabit24@usj.ac.ug"
                password = "o764176632"
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Mbarara Grocery Portal",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = "Select your role & sign in to access portal",
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(20.dp))

            // Role Dropdown Selector
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded },
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = selectedRole.label,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Select Portal Role") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    modifier = Modifier.menuAnchor().fillMaxWidth()
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    UserRole.entries.forEach { role ->
                        DropdownMenuItem(
                            text = { Text("${role.label} - ${role.description}") },
                            onClick = {
                                updateRoleCredentials(role)
                                expanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                    errorMessage = null
                },
                label = { Text("Email Address") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                    errorMessage = null
                },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            if (errorMessage != null) {
                Spacer(modifier = Modifier.height(12.dp))
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFFFEBEE)),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = errorMessage!!,
                        color = Color(0xFFC62828),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(10.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    authViewModel.login(
                        email = email,
                        password = password,
                        role = selectedRole,
                        onSuccess = { onLoginSuccess(selectedRole) },
                        onError = { err -> errorMessage = err }
                    )
                },
                modifier = Modifier.fillMaxWidth().height(50.dp)
            ) {
                if (authState is AuthState.Loading) {
                    CircularProgressIndicator(color = Color.White, modifier = Modifier.size(24.dp))
                } else {
                    Text("Login to ${selectedRole.label}", fontSize = 16.sp)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedButton(
                onClick = { updateRoleCredentials(selectedRole) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Auto-Fill Demo Credentials for ${selectedRole.label}", fontSize = 12.sp)
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Don't have an account?")
                TextButton(onClick = onNavigateToRegister) {
                    Text("Register Now", fontWeight = FontWeight.Bold)
                }
            }
        }

        DeveloperFooter(modifier = Modifier.align(Alignment.BottomEnd))
    }
}
