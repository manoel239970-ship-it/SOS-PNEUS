package com.example.sospneus.di

import android.app.Application
import com.example.sospneus.data.database.SosPneusDatabase
import com.example.sospneus.data.repository.FavoriteContactRepositoryImpl
import com.example.sospneus.data.repository.TutorialRepositoryImpl
import com.example.sospneus.domain.repository.FavoriteContactRepository
import com.example.sospneus.domain.repository.TutorialRepository
import com.example.sospneus.utils.GPSHelper
import com.example.sospneus.viewmodel.*

/**
 * Container de dependências simples (DI manual)
 */
class AppContainer(private val application: Application) {
    
    // Database
    private val database: SosPneusDatabase = SosPneusDatabase.getDatabase(application)
    
    // Repositories
    val tutorialRepository: TutorialRepository = TutorialRepositoryImpl(
        tutorialStepDao = database.tutorialStepDao()
    )
    
    val favoriteContactRepository: FavoriteContactRepository = FavoriteContactRepositoryImpl(
        favoriteContactDao = database.favoriteContactDao()
    )
    
    // Utils
    val gpsHelper: GPSHelper = GPSHelper(application)
    
    // ViewModels (serão criados na MainActivity)
    fun createTutorialViewModel(): TutorialViewModel {
        return TutorialViewModel(tutorialRepository)
    }
    
    fun createEmergencyViewModel(): EmergencyViewModel {
        return EmergencyViewModel(favoriteContactRepository)
    }
    
    fun createFavoritesViewModel(): FavoritesViewModel {
        return FavoritesViewModel(favoriteContactRepository)
    }
    
    fun createMapViewModel(): MapViewModel {
        return MapViewModel(favoriteContactRepository, application)
    }
}

