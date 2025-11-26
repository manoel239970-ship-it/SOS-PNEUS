package com.example.sospneus.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sospneus.data.model.FavoriteContact
import com.example.sospneus.domain.repository.FavoriteContactRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel para gerenciar o estado da tela de favoritos
 */
class FavoritesViewModel(
    private val favoriteContactRepository: FavoriteContactRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(FavoritesUiState())
    val uiState: StateFlow<FavoritesUiState> = _uiState.asStateFlow()
    
    init {
        loadContacts()
    }
    
    private fun loadContacts() {
        viewModelScope.launch {
            favoriteContactRepository.getAllContacts().collect { contacts ->
                _uiState.value = _uiState.value.copy(
                    contacts = contacts,
                    isLoading = false
                )
            }
        }
    }
    
    fun addContact(contact: FavoriteContact) {
        viewModelScope.launch {
            try {
                favoriteContactRepository.insertContact(contact)
                _uiState.value = _uiState.value.copy(
                    showAddDialog = false,
                    error = null
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    error = e.message ?: "Erro ao adicionar contato"
                )
            }
        }
    }
    
    fun updateContact(contact: FavoriteContact) {
        viewModelScope.launch {
            try {
                favoriteContactRepository.updateContact(contact)
                _uiState.value = _uiState.value.copy(
                    showEditDialog = false,
                    editingContact = null,
                    error = null
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    error = e.message ?: "Erro ao atualizar contato"
                )
            }
        }
    }
    
    fun deleteContact(contact: FavoriteContact) {
        viewModelScope.launch {
            try {
                favoriteContactRepository.deleteContact(contact)
                _uiState.value = _uiState.value.copy(error = null)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    error = e.message ?: "Erro ao deletar contato"
                )
            }
        }
    }
    
    fun showAddDialog() {
        _uiState.value = _uiState.value.copy(showAddDialog = true)
    }
    
    fun hideAddDialog() {
        _uiState.value = _uiState.value.copy(showAddDialog = false)
    }
    
    fun showEditDialog(contact: FavoriteContact) {
        _uiState.value = _uiState.value.copy(
            showEditDialog = true,
            editingContact = contact
        )
    }
    
    fun hideEditDialog() {
        _uiState.value = _uiState.value.copy(
            showEditDialog = false,
            editingContact = null
        )
    }
    
    fun clearError() {
        _uiState.value = _uiState.value.copy(error = null)
    }
}

/**
 * Estado da UI de Favoritos
 */
data class FavoritesUiState(
    val contacts: List<FavoriteContact> = emptyList(),
    val isLoading: Boolean = true,
    val showAddDialog: Boolean = false,
    val showEditDialog: Boolean = false,
    val editingContact: FavoriteContact? = null,
    val error: String? = null
)

