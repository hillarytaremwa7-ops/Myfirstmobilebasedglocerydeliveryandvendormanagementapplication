package com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.AccountStatus
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.AvailabilityStatus
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.Middleman
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.Rider
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.User
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.UserRole
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.Vendor
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.repository.GroceryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.UUID

class AuthViewModel(private val repository: GroceryRepository) : ViewModel() {

    val currentUser = repository.currentUser

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState.asStateFlow()

    fun login(email: String, password: String, role: UserRole, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            val trimmedEmail = email.trim()
            val trimmedPass = password.trim()

            if (trimmedEmail.isEmpty()) {
                val err = "Please enter your email address."
                _authState.value = AuthState.Error(err)
                onError(err)
                return@launch
            }

            if (role == UserRole.ADMIN) {
                val isValidEmail = trimmedEmail.equals("htaremwabit24@usj.ac.ug", ignoreCase = true)
                val isValidPass = trimmedPass == "o764176632" || trimmedPass == "0764176632"

                if (!isValidEmail || !isValidPass) {
                    val err = "Admin login failed: Invalid credentials. Only htaremwabit24@usj.ac.ug is authorized."
                    _authState.value = AuthState.Error(err)
                    onError(err)
                    return@launch
                }

                val adminUser = User(
                    userId = "usr_admin_hillary",
                    firstName = "Taremwa",
                    lastName = "Hillary",
                    fullName = "Taremwa Hillary",
                    email = "htaremwabit24@usj.ac.ug",
                    phone = "+256 764 176632",
                    role = UserRole.ADMIN,
                    address = "Mbarara City Council Market Admin Office",
                    enableSmsNotifications = true,
                    enableEmailNotifications = true,
                    accountStatus = AccountStatus.APPROVED
                )
                repository.insertUser(adminUser)
                repository.setCurrentUser(adminUser)
                _authState.value = AuthState.Success(adminUser)
                onSuccess()
                return@launch
            }

            // Look up existing registered user in database
            val existingUser = repository.getUserByEmail(trimmedEmail)

            if (existingUser == null) {
                val err = "Account not found for '$trimmedEmail'. Please register first to gain access."
                _authState.value = AuthState.Error(err)
                onError(err)
                return@launch
            }

            if (existingUser.role != role) {
                val err = "Account exists as ${existingUser.role.label}. Please select ${existingUser.role.label} to log in or register a new account."
                _authState.value = AuthState.Error(err)
                onError(err)
                return@launch
            }

            repository.setCurrentUser(existingUser)
            _authState.value = AuthState.Success(existingUser)
            onSuccess()
        }
    }

    fun register(
        firstName: String,
        lastName: String,
        email: String,
        phone: String,
        address: String,
        password: String,
        enableSmsNotifications: Boolean,
        enableEmailNotifications: Boolean,
        role: UserRole,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            try {
                if (role == UserRole.ADMIN) {
                    val err = "Admin accounts cannot be self-registered. Use authorized admin credentials."
                    _authState.value = AuthState.Error(err)
                    onError(err)
                    return@launch
                }

                val trimmedEmail = email.trim()
                if (trimmedEmail.isEmpty()) {
                    val err = "Email address is required."
                    _authState.value = AuthState.Error(err)
                    onError(err)
                    return@launch
                }

                val trimmedPass = password.trim()
                if (trimmedPass.length !in 5..10) {
                    val err = "Password must be between 5 and 10 digits long."
                    _authState.value = AuthState.Error(err)
                    onError(err)
                    return@launch
                }

                if (!trimmedPass.all { it.isDigit() }) {
                    val err = "Password must contain numbers only (5-10 digits)."
                    _authState.value = AuthState.Error(err)
                    onError(err)
                    return@launch
                }

                val existing = repository.getUserByEmail(trimmedEmail)
                if (existing != null) {
                    val err = "An account with email '$trimmedEmail' already exists. Please log in."
                    _authState.value = AuthState.Error(err)
                    onError(err)
                    return@launch
                }

                val fName = firstName.trim()
                val lName = lastName.trim()
                val full = "$fName $lName".trim()

                val newUser = User(
                    userId = "usr_" + UUID.randomUUID().toString().take(8),
                    firstName = fName,
                    lastName = lName,
                    fullName = if (full.isNotEmpty()) full else "User",
                    email = trimmedEmail,
                    phone = phone.trim(),
                    role = role,
                    address = address.ifEmpty { "Mbarara City, Uganda" },
                    enableSmsNotifications = enableSmsNotifications,
                    enableEmailNotifications = enableEmailNotifications,
                    accountStatus = AccountStatus.APPROVED
                )
                repository.insertUser(newUser)

                when (role) {
                    UserRole.VENDOR -> {
                        val newVendor = Vendor(
                            id = "v_" + UUID.randomUUID().toString().take(6),
                            userId = newUser.userId,
                            name = if (full.isNotEmpty()) "$full Groceries" else "Mbarara Fresh Groceries",
                            category = "Fresh Groceries",
                            rating = 5.0,
                            address = address.ifEmpty { "Mbarara Central Market, Block A" },
                            phone = phone.trim(),
                            status = AccountStatus.APPROVED
                        )
                        repository.insertVendor(newVendor)
                    }
                    UserRole.RIDER -> {
                        val newRider = Rider(
                            riderId = "r_" + UUID.randomUUID().toString().take(6),
                            userId = newUser.userId,
                            fullName = if (full.isNotEmpty()) full else "Boda Rider",
                            phone = phone.trim(),
                            motorcycleNumber = "UFE " + (100..999).random() + "B",
                            licenseNumber = "DL-" + (100000..999999).random(),
                            nationalId = "CM" + UUID.randomUUID().toString().take(10).uppercase(),
                            availabilityStatus = com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.AvailabilityStatus.ONLINE,
                            verificationStatus = AccountStatus.APPROVED,
                            totalDeliveries = 0
                        )
                        repository.insertRider(newRider)
                    }
                    UserRole.MIDDLEMAN -> {
                        val newMiddleman = Middleman(
                            middlemanId = "m_" + UUID.randomUUID().toString().take(6),
                            userId = newUser.userId,
                            fullName = if (full.isNotEmpty()) full else "Market Coordinator",
                            phone = phone.trim(),
                            serviceArea = address.ifEmpty { "Mbarara City & Central Market" },
                            verificationStatus = AccountStatus.APPROVED,
                            availabilityStatus = AvailabilityStatus.ONLINE,
                            totalTransactions = 0
                        )
                        repository.insertMiddleman(newMiddleman)
                    }
                    else -> {}
                }

                repository.setCurrentUser(newUser)
                _authState.value = AuthState.Success(newUser)
                onSuccess()
            } catch (e: Exception) {
                val err = e.localizedMessage ?: "Registration failed"
                _authState.value = AuthState.Error(err)
                onError(err)
            }
        }
    }

    fun logout() {
        repository.setCurrentUser(null)
        _authState.value = AuthState.Idle
    }
}

sealed class AuthState {
    object Idle : AuthState()
    object Loading : AuthState()
    data class Success(val user: User) : AuthState()
    data class Error(val message: String) : AuthState()
}
