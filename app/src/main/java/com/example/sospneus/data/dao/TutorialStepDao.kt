package com.example.sospneus.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sospneus.data.entity.TutorialStepEntity
import kotlinx.coroutines.flow.Flow

/**
 * DAO para operações com passos do tutorial
 */
@Dao
interface TutorialStepDao {
    
    @Query("SELECT * FROM tutorial_steps ORDER BY `order` ASC")
    fun getAllSteps(): Flow<List<TutorialStepEntity>>
    
    @Query("SELECT * FROM tutorial_steps WHERE id = :id")
    suspend fun getStepById(id: Int): TutorialStepEntity?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStep(step: TutorialStepEntity)
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllSteps(steps: List<TutorialStepEntity>)
    
    @Query("DELETE FROM tutorial_steps")
    suspend fun deleteAllSteps()
}

