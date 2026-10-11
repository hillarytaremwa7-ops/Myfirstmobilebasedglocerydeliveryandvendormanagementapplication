package com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.UserRole
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.components.DeveloperFooter
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.viewmodel.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    authViewModel: AuthViewModel,
    onRegisterSuccess: (UserRole) -> Unit,
    onNavigateToLogin: () -> Unit
) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var enableSms by remember { mutableStateOf(true) }
    var enableEmail by remember { mutableStateOf(true) }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var selectedRole by remember { mutableStateOf(UserRole.CUSTOMER) }
    var expanded by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 24.dp, end = 24.dp, top = 24.dp, bottom = 36.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Create an Account",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = "Join Mbarara Grocery Delivery & Vendor Platform",
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(16.dp))

            if (errorMessage != null) {
                Text(
                    text = errorMessage!!,
                    color = Color.Red,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
            }

            // First Name & Last Name Side by Side
            Row(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = firstName,
                    onValueChange = {
                        firstName = it
                        errorMessage = null
                    },
                    label = { Text("First Name") },
                    modifier = Modifier.weight(1f),
                    singleLine = true
                )
                Spacer(modifier = Modifier.width(8.dp))
                OutlinedTextField(
                    value = lastName,
                    onValueChange = {
                        lastName = it
                        errorMessage = null
                    },
                    label = { Text("Last Name") },
                    modifier = Modifier.weight(1f),
                    singleLine = true
                )
            }
            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = phone,
                onValueChange = {
                    phone = it
                    errorMessage = null
                },
                label = { Text("Phone Number (+256...)") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = address,
                onValueChange = {
                    address = it
                    errorMessage = null
                },
                label = { Text("Address / Location (Mbarara City)") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                    errorMessage = null
                },
                label = { Text("Email Address") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(10.dp))

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded },
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = selectedRole.label,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Register As") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    modifier = Modifier.menuAnchor().fillMaxWidth()
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    UserRole.entries.filter { it != UserRole.ADMIN }.forEach { role ->
                        DropdownMenuItem(
                            text = { Text("${role.label} - ${role.description}") },
                            onClick = {
                                selectedRole = role
                                expanded = false
                                errorMessage = null
                            }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))

            Text("Notification Preferences", fontSize = 13.sp, fontWeight = FontWeight.Bold, modifier = Modifier.align(Alignment.Start))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(checked = enableSms, onCheckedChange = { enableSms = it })
                Text("SMS Notifications", fontSize = 13.sp)
                Spacer(modifier = Modifier.width(16.dp))
                Checkbox(checked = enableEmail, onCheckedChange = { enableEmail = it })
                Text("Email Notifications", fontSize = 13.sp)
            }
            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                    errorMessage = null
                },
                label = { Text("Numeric Password (5-10 digits)") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = confirmPassword,
                onValueChange = {
                    confirmPassword = it
                    errorMessage = null
                },
                label = { Text("Confirm Numeric Password") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (firstName.isEmpty() || lastName.isEmpty()) {
                        errorMessage = "Please enter both First Name and Last Name."
                        return@Button
                    }

                    if (password.length !in 5..10) {
                        errorMessage = "Password must be between 5 and 10 digits long."
                        return@Button
                    }

                    if (!password.all { it.isDigit() }) {
                        errorMessage = "Password must contain numbers only (5-10 digits)."
                        return@Button
                    }

                    if (password != confirmPassword) {
                        errorMessage = "Passwords do not match."
                        return@Button
                    }

                    authViewModel.register(
                        firstName = firstName,
                        lastName = lastName,
                        email = email,
                        phone = phone,
                        address = address,
                        password = password,
                        enableSmsNotifications = enableSms,
                        enableEmailNotifications = enableEmail,
                        role = selectedRole,
                        onSuccess = { onRegisterSuccess(selectedRole) },
                        onError = { err -> errorMessage = err }
                    )
                },
                modifier = Modifier.fillMaxWidth().height(50.dp)
            ) {
                Text("Register Account", fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Already have an account?")
                TextButton(onClick = onNavigateToLogin) {
                    Text("Login")
                }
            }
        }

        DeveloperFooter(modifier = Modifier.align(Alignment.BottomEnd))
    }
}
