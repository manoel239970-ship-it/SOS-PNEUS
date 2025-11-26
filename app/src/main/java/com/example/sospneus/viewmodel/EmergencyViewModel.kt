package com.example.sospneus.viewmodel

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sospneus.data.model.FavoriteContact
import com.example.sospneus.domain.repository.FavoriteContactRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel para gerenciar o estado da tela de emergência
 */
class EmergencyViewModel(
    private val favoriteContactRepository: FavoriteContactRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(EmergencyUiState())
    val uiState: StateFlow<EmergencyUiState> = _uiState.asStateFlow()
    
    init {
        loadFavoriteContacts()
    }
    
    private fun loadFavoriteContacts() {
        viewModelScope.launch {
            favoriteContactRepository.getAllContacts().collect { contacts ->
                _uiState.value = _uiState.value.copy(contacts = contacts)
            }
        }
    }
    
    fun callContact(context: Context, contact: FavoriteContact) {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:${contact.phone}")
        }
        context.startActivity(intent)
    }
    
    fun sendMessage(context: Context, contact: FavoriteContact) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("smsto:${contact.phone}")
            putExtra("sms_body", "Preciso de ajuda com pneu furado. Minha localização: [GPS]")
        }
        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(intent)
        }
    }
    
    fun selectContact(contact: FavoriteContact) {
        _uiState.value = _uiState.value.copy(selectedContact = contact)
    }
    
    fun clearSelection() {
        _uiState.value = _uiState.value.copy(selectedContact = null)
    }
}

/**
 * Estado da UI de Emergência
 */
data class EmergencyUiState(
    val contacts: List<FavoriteContact> = emptyList(),
    val selectedContact: FavoriteContact? = null
)

