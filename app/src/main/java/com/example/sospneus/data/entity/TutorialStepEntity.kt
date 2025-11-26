package com.example.sospneus.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entidade Room para armazenar passos do tutorial localmente
 */
@Entity(tableName = "tutorial_steps")
data class TutorialStepEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val description: String,
    val imageResource: String,
    val order: Int
)

