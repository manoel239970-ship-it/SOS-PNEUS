package com.example.sospneus.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sospneus.data.model.TutorialStep
import com.example.sospneus.domain.repository.TutorialRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

/**
 * ViewModel para gerenciar o estado da tela de tutorial
 */
class TutorialViewModel(
    private val tutorialRepository: TutorialRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(TutorialUiState())
    val uiState: StateFlow<TutorialUiState> = _uiState.asStateFlow()
    
    init {
        loadTutorialSteps()
    }
    
    private fun loadTutorialSteps() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            
            try {
                // Verifica se há passos no banco
                val firstSteps = tutorialRepository.getAllSteps()
                    .catch { e ->
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            error = e.message
                        )
                    }
                    .first()
                
                if (firstSteps.isEmpty()) {
                    // Se não houver passos, carrega os padrão
                    val defaultSteps = tutorialRepository.loadDefaultSteps()
                    tutorialRepository.insertAllSteps(defaultSteps)
                    _uiState.value = _uiState.value.copy(
                        steps = defaultSteps,
                        isLoading = false
                    )
                } else {
                    _uiState.value = _uiState.value.copy(
                        steps = firstSteps,
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
        
        // Observa mudanças no banco de dados
        viewModelScope.launch {
            tutorialRepository.getAllSteps()
                .catch { e ->
                    _uiState.value = _uiState.value.copy(
                        error = e.message
                    )
                }
                .collect { steps ->
                    if (steps.isNotEmpty()) {
                        _uiState.value = _uiState.value.copy(steps = steps)
                    }
                }
        }
    }
    
    fun selectStep(step: TutorialStep) {
        _uiState.value = _uiState.value.copy(selectedStep = step)
    }
    
    fun clearError() {
        _uiState.value = _uiState.value.copy(error = null)
    }
}

/**
 * Estado da UI do Tutorial
 */
data class TutorialUiState(
    val steps: List<TutorialStep> = emptyList(),
    val selectedStep: TutorialStep? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)

