package com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.vendor

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.components.DeveloperFooter
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.theme.VendorOrange
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.viewmodel.VendorViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditProductScreen(
    vendorViewModel: VendorViewModel,
    onBack: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("Vegetables") }
    var price by remember { mutableStateOf("") }
    var unit by remember { mutableStateOf("1 kg") }
    var description by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }

    var expandedCategory by remember { mutableStateOf(false) }
    var expandedUnit by remember { mutableStateOf(false) }

    val categories = listOf("Vegetables", "Fruits", "Tubers", "Cereals", "Pulses", "Meat", "Dairy Products", "Eggs", "Spices")
    val units = listOf("1 kg", "1 Bunch", "1 Heap", "1 Piece", "1 Liter", "1 Tray (30 Eggs)", "1 Bundle", "1 Bag")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add Grocery Product") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = VendorOrange,
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
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 32.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Product Name (e.g. Tomatoes, Matooke)") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(10.dp))

                ExposedDropdownMenuBox(
                    expanded = expandedCategory,
                    onExpandedChange = { expandedCategory = !expandedCategory }
                ) {
                    OutlinedTextField(
                        value = category,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Category") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedCategory) },
                        modifier = Modifier.menuAnchor().fillMaxWidth()
                    )
                    ExposedDropdownMenu(
                        expanded = expandedCategory,
                        onDismissRequest = { expandedCategory = false }
                    ) {
                        categories.forEach { item ->
                            DropdownMenuItem(
                                text = { Text(item) },
                                onClick = {
                                    category = item
                                    expandedCategory = false
                                }
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = price,
                    onValueChange = { price = it },
                    label = { Text("Price in UGX (e.g. 3000)") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(10.dp))

                ExposedDropdownMenuBox(
                    expanded = expandedUnit,
                    onExpandedChange = { expandedUnit = !expandedUnit }
                ) {
                    OutlinedTextField(
                        value = unit,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Measurement Unit") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedUnit) },
                        modifier = Modifier.menuAnchor().fillMaxWidth()
                    )
                    ExposedDropdownMenu(
                        expanded = expandedUnit,
                        onDismissRequest = { expandedUnit = false }
                    ) {
                        units.forEach { item ->
                            DropdownMenuItem(
                                text = { Text(item) },
                                onClick = {
                                    unit = item
                                    expandedUnit = false
                                }
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = quantity,
                    onValueChange = { quantity = it },
                    label = { Text("Stock Quantity (e.g. 50)") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Product Description") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        vendorViewModel.addProduct(
                            name = name,
                            category = category,
                            price = price.toDoubleOrNull() ?: 3000.0,
                            unit = unit,
                            description = description,
                            stockQuantity = quantity.toIntOrNull() ?: 50
                        )
                        onBack()
                    },
                    modifier = Modifier.fillMaxWidth().height(50.dp)
                ) {
                    Text("Save Product", fontSize = 16.sp)
                }
            }

            DeveloperFooter(modifier = Modifier.align(Alignment.BottomEnd))
        }
    }
}
