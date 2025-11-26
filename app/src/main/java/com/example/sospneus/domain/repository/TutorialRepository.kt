package com.example.sospneus.domain.repository

import com.example.sospneus.data.model.TutorialStep
import kotlinx.coroutines.flow.Flow

/**
 * Repository para gerenciar dados do tutorial
 */
interface TutorialRepository {
    fun getAllSteps(): Flow<List<TutorialStep>>
    suspend fun getStepById(id: Int): TutorialStep?
    suspend fun insertStep(step: TutorialStep)
    suspend fun insertAllSteps(steps: List<TutorialStep>)
    suspend fun loadDefaultSteps(): List<TutorialStep>
}

