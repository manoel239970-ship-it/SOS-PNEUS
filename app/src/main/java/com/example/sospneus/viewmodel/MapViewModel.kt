package com.example.sospneus.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sospneus.data.model.FavoriteContact
import com.example.sospneus.data.model.LocationData
import com.example.sospneus.domain.repository.FavoriteContactRepository
import com.example.sospneus.utils.GPSHelper
import com.example.sospneus.utils.NetworkUtils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel para gerenciar o estado da tela de mapa
 */
class MapViewModel(
    private val favoriteContactRepository: FavoriteContactRepository,
    private val context: Context
) : ViewModel() {
    
    private val gpsHelper = GPSHelper(context)
    
    private val _uiState = MutableStateFlow(MapUiState())
    val uiState: StateFlow<MapUiState> = _uiState.asStateFlow()
    
    init {
        loadContacts()
        observeNetworkStatus()
        requestLocation()
    }
    
    private fun loadContacts() {
        viewModelScope.launch {
            favoriteContactRepository.getAllContacts().collect { contacts ->
                _uiState.value = _uiState.value.copy(contacts = contacts)
            }
        }
    }
    
    private fun observeNetworkStatus() {
        viewModelScope.launch {
            NetworkUtils.observeNetworkStatus(context).collect { isOnline ->
                _uiState.value = _uiState.value.copy(isOnline = isOnline)
            }
        }
    }
    
    fun requestLocation() {
        if (!gpsHelper.hasLocationPermission()) {
            _uiState.value = _uiState.value.copy(
                locationError = "Permissão de localização necessária"
            )
            return
        }
        
        if (!gpsHelper.isGPSEnabled()) {
            _uiState.value = _uiState.value.copy(
                locationError = "GPS desabilitado. Por favor, ative o GPS."
            )
            return
        }
        
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoadingLocation = true, locationError = null)
            
            try {
                val location = gpsHelper.getCurrentLocation()
                if (location != null) {
                    _uiState.value = _uiState.value.copy(
                        currentLocation = location,
                        isLoadingLocation = false
                    )
                } else {
                    // Tenta obter última localização conhecida
                    val lastLocation = gpsHelper.getLastKnownLocation()
                    _uiState.value = _uiState.value.copy(
                        currentLocation = lastLocation,
                        isLoadingLocation = false,
                        locationError = if (lastLocation == null) "Não foi possível obter localização" else null
                    )
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoadingLocation = false,
                    locationError = e.message ?: "Erro ao obter localização"
                )
            }
        }
    }
    
    fun clearLocationError() {
        _uiState.value = _uiState.value.copy(locationError = null)
    }
}

/**
 * Estado da UI do Mapa
 */
data class MapUiState(
    val contacts: List<FavoriteContact> = emptyList(),
    val currentLocation: LocationData? = null,
    val isLoadingLocation: Boolean = false,
    val isOnline: Boolean = false,
    val locationError: String? = null
)

