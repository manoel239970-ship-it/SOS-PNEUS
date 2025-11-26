package com.example.sospneus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.sospneus.di.AppContainer
import com.example.sospneus.ui.navigation.NavGraph
import com.example.sospneus.ui.theme.SOSPneusTheme

class MainActivity : ComponentActivity() {
    
    private lateinit var appContainer: AppContainer
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Inicializa o container de dependências
        appContainer = AppContainer(application)
        
        enableEdgeToEdge()
        setContent {
            SOSPneusTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    
                    NavGraph(
                        navController = navController,
                        tutorialViewModel = appContainer.createTutorialViewModel(),
                        emergencyViewModel = appContainer.createEmergencyViewModel(),
                        favoritesViewModel = appContainer.createFavoritesViewModel(),
                        mapViewModel = appContainer.createMapViewModel()
                    )
                }
            }
        }
    }
}