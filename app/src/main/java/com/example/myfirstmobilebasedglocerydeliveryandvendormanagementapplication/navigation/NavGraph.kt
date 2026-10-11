package com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.UserRole
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.admin.AdminDashboardScreen
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.auth.LoginScreen
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.auth.OnboardingScreen
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.auth.RegisterScreen
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.auth.SplashScreen
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.customer.CartScreen
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.customer.CheckoutScreen
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.customer.CustomerHomeScreen
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.customer.CustomerOrdersScreen
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.customer.CustomerProfileScreen
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.customer.OrderConfirmationScreen
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.customer.ProductDetailScreen
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.middleman.MiddlemanDashboardScreen
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.rider.RiderDashboardScreen
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.vendor.AddEditProductScreen
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.vendor.VendorDashboardScreen
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.viewmodel.AdminViewModel
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.viewmodel.AuthViewModel
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.viewmodel.CustomerViewModel
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.viewmodel.MiddlemanViewModel
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.viewmodel.RiderViewModel
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.viewmodel.VendorViewModel

object NavRoutes {
    const val SPLASH = "splash"
    const val ONBOARDING = "onboarding"
    const val LOGIN = "login"
    const val REGISTER = "register"

    const val CUSTOMER_HOME = "customer_home"
    const val PRODUCT_DETAIL = "product_detail"
    const val CART = "cart"
    const val CHECKOUT = "checkout"
    const val ORDER_CONFIRMATION = "order_confirmation"
    const val CUSTOMER_ORDERS = "customer_orders"
    const val CUSTOMER_PROFILE = "customer_profile"

    const val VENDOR_DASHBOARD = "vendor_dashboard"
    const val ADD_PRODUCT = "add_product"

    const val RIDER_DASHBOARD = "rider_dashboard"
    const val MIDDLEMAN_DASHBOARD = "middleman_dashboard"
    const val ADMIN_DASHBOARD = "admin_dashboard"
}

@Composable
fun GroceryNavGraph(
    navController: NavHostController = rememberNavController(),
    authViewModel: AuthViewModel,
    customerViewModel: CustomerViewModel,
    vendorViewModel: VendorViewModel,
    riderViewModel: RiderViewModel,
    middlemanViewModel: MiddlemanViewModel,
    adminViewModel: AdminViewModel
) {
    NavHost(
        navController = navController,
        startDestination = NavRoutes.SPLASH
    ) {
        composable(NavRoutes.SPLASH) {
            SplashScreen(onNavigateToNext = {
                navController.navigate(NavRoutes.ONBOARDING) {
                    popUpTo(NavRoutes.SPLASH) { inclusive = true }
                }
            })
        }

        composable(NavRoutes.ONBOARDING) {
            OnboardingScreen(onContinue = {
                navController.navigate(NavRoutes.LOGIN) {
                    popUpTo(NavRoutes.ONBOARDING) { inclusive = true }
                }
            })
        }

        composable(NavRoutes.LOGIN) {
            LoginScreen(
                authViewModel = authViewModel,
                onLoginSuccess = { role ->
                    val route = when (role) {
                        UserRole.CUSTOMER -> NavRoutes.CUSTOMER_HOME
                        UserRole.VENDOR -> NavRoutes.VENDOR_DASHBOARD
                        UserRole.RIDER -> NavRoutes.RIDER_DASHBOARD
                        UserRole.MIDDLEMAN -> NavRoutes.MIDDLEMAN_DASHBOARD
                        UserRole.ADMIN -> NavRoutes.ADMIN_DASHBOARD
                    }
                    navController.navigate(route) {
                        popUpTo(NavRoutes.LOGIN) { inclusive = true }
                    }
                },
                onNavigateToRegister = { navController.navigate(NavRoutes.REGISTER) }
            )
        }

        composable(NavRoutes.REGISTER) {
            RegisterScreen(
                authViewModel = authViewModel,
                onRegisterSuccess = { role ->
                    val route = when (role) {
                        UserRole.CUSTOMER -> NavRoutes.CUSTOMER_HOME
                        UserRole.VENDOR -> NavRoutes.VENDOR_DASHBOARD
                        UserRole.RIDER -> NavRoutes.RIDER_DASHBOARD
                        UserRole.MIDDLEMAN -> NavRoutes.MIDDLEMAN_DASHBOARD
                        UserRole.ADMIN -> NavRoutes.ADMIN_DASHBOARD
                    }
                    navController.navigate(route) {
                        popUpTo(NavRoutes.REGISTER) { inclusive = true }
                    }
                },
                onNavigateToLogin = { navController.popBackStack() }
            )
        }

        // Customer Routes
        composable(NavRoutes.CUSTOMER_HOME) {
            CustomerHomeScreen(
                customerViewModel = customerViewModel,
                onProductClick = { navController.navigate(NavRoutes.PRODUCT_DETAIL) },
                onOpenCart = { navController.navigate(NavRoutes.CART) },
                onOpenOrders = { navController.navigate(NavRoutes.CUSTOMER_ORDERS) },
                onOpenProfile = { navController.navigate(NavRoutes.CUSTOMER_PROFILE) },
                onLogout = {
                    authViewModel.logout()
                    navController.navigate(NavRoutes.LOGIN) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }

        composable(NavRoutes.PRODUCT_DETAIL) {
            val products by customerViewModel.allProducts.collectAsState()
            val firstProduct = products.firstOrNull()
            if (firstProduct != null) {
                ProductDetailScreen(
                    product = firstProduct,
                    onBack = { navController.popBackStack() },
                    onAddToCart = { qty ->
                        customerViewModel.addToCart(firstProduct, qty)
                        navController.popBackStack()
                    }
                )
            }
        }

        composable(NavRoutes.CART) {
            CartScreen(
                customerViewModel = customerViewModel,
                onBack = { navController.popBackStack() },
                onProceedToCheckout = { navController.navigate(NavRoutes.CHECKOUT) }
            )
        }

        composable(NavRoutes.CHECKOUT) {
            CheckoutScreen(
                customerViewModel = customerViewModel,
                onBack = { navController.popBackStack() },
                onOrderPlaced = {
                    navController.navigate(NavRoutes.ORDER_CONFIRMATION) {
                        popUpTo(NavRoutes.CUSTOMER_HOME) { inclusive = false }
                    }
                }
            )
        }

        composable(NavRoutes.ORDER_CONFIRMATION) {
            val orders by customerViewModel.allOrders.collectAsState()
            val lastOrder = orders.firstOrNull()
            if (lastOrder != null) {
                OrderConfirmationScreen(
                    order = lastOrder,
                    onDone = { navController.navigate(NavRoutes.CUSTOMER_ORDERS) }
                )
            }
        }

        composable(NavRoutes.CUSTOMER_ORDERS) {
            CustomerOrdersScreen(
                customerViewModel = customerViewModel,
                onBack = { navController.popBackStack() }
            )
        }

        composable(NavRoutes.CUSTOMER_PROFILE) {
            CustomerProfileScreen(
                authViewModel = authViewModel,
                onBack = { navController.popBackStack() },
                onLogout = {
                    authViewModel.logout()
                    navController.navigate(NavRoutes.LOGIN) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }

        // Vendor Routes
        composable(NavRoutes.VENDOR_DASHBOARD) {
            VendorDashboardScreen(
                vendorViewModel = vendorViewModel,
                onAddProduct = { navController.navigate(NavRoutes.ADD_PRODUCT) },
                onLogout = {
                    authViewModel.logout()
                    navController.navigate(NavRoutes.LOGIN) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }

        composable(NavRoutes.ADD_PRODUCT) {
            AddEditProductScreen(
                vendorViewModel = vendorViewModel,
                onBack = { navController.popBackStack() }
            )
        }

        // Rider Route
        composable(NavRoutes.RIDER_DASHBOARD) {
            RiderDashboardScreen(
                riderViewModel = riderViewModel,
                onLogout = {
                    authViewModel.logout()
                    navController.navigate(NavRoutes.LOGIN) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }

        // Middleman Route
        composable(NavRoutes.MIDDLEMAN_DASHBOARD) {
            MiddlemanDashboardScreen(
                middlemanViewModel = middlemanViewModel,
                onLogout = {
                    authViewModel.logout()
                    navController.navigate(NavRoutes.LOGIN) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }

        // Admin Route
        composable(NavRoutes.ADMIN_DASHBOARD) {
            AdminDashboardScreen(
                adminViewModel = adminViewModel,
                onLogout = {
                    authViewModel.logout()
                    navController.navigate(NavRoutes.LOGIN) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }
    }
}
