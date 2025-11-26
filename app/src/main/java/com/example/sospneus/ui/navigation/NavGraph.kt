package com.example.sospneus.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sospneus.ui.screens.*
import com.example.sospneus.viewmodel.*

/**
 * Definição das rotas de navegação
 */
sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Tutorial : Screen("tutorial")
    object Emergency : Screen("emergency")
    object Favorites : Screen("favorites")
    object Map : Screen("map")
}

/**
 * Grafo de navegação do aplicativo
 */
@Composable
fun NavGraph(
    navController: NavHostController,
    tutorialViewModel: TutorialViewModel,
    emergencyViewModel: EmergencyViewModel,
    favoritesViewModel: FavoritesViewModel,
    mapViewModel: MapViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                onNavigateToTutorial = { navController.navigate(Screen.Tutorial.route) },
                onNavigateToEmergency = { navController.navigate(Screen.Emergency.route) },
                onNavigateToFavorites = { navController.navigate(Screen.Favorites.route) },
                onNavigateToMap = { navController.navigate(Screen.Map.route) }
            )
        }
        
        composable(Screen.Tutorial.route) {
            TutorialScreen(
                viewModel = tutorialViewModel,
                onNavigateBack = { navController.popBackStack() }
            )
        }
        
        composable(Screen.Emergency.route) {
            EmergencyScreen(
                viewModel = emergencyViewModel,
                onNavigateBack = { navController.popBackStack() }
            )
        }
        
        composable(Screen.Favorites.route) {
            FavoritesScreen(
                viewModel = favoritesViewModel,
                onNavigateBack = { navController.popBackStack() }
            )
        }
        
        composable(Screen.Map.route) {
            MapScreen(
                viewModel = mapViewModel,
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}

