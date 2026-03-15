package com.example.sportshop.ui.presentation.screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

object AppRoutes {
    const val MAIN = "main"
    const val LOGIN = "login"
    const val REGISTER = "register"
    const val CATALOG = "catalog"
    const val SPLASH = "splash"
    const val FORGOT_PASSWORD = "forgot_password"
    const val OTP = "otp"
    const val NEW_PASSWORD = "new_password"
    const val PROFILE = "profile"
}


@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppRoutes.LOGIN
    ) {
        composable(AppRoutes.MAIN) {
            ProductsScreen(navController = navController)
        }

        composable(AppRoutes.LOGIN) {
            LoginScreen(navController = navController)
        }

        composable(AppRoutes.REGISTER) {
            RegisterScreen(navController = navController)
        }

        composable(AppRoutes.CATALOG) {
            CatalogPlaceholderScreen(navController = navController)
        }

        composable(AppRoutes.SPLASH) {
            SplashScreen(navController = navController)
        }
        composable(AppRoutes.FORGOT_PASSWORD) {
            ForgotPasswordScreen(navController = navController)
        }
        composable(AppRoutes.OTP) {
            OtpVerificationScreen(navController = navController)
        }
        composable(AppRoutes.NEW_PASSWORD) {
            NewPasswordScreen(navController = navController)
        }
        composable(AppRoutes.PROFILE) {
            ProfileScreen(navController = navController)
        }
    }
}