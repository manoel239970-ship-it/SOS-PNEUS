package com.example.sospneus.data.model

/**
 * Modelo de dados para um passo do tutorial de troca de pneu
 */
data class TutorialStep(
    val id: Int,
    val title: String,
    val description: String,
    val imageResource: String, // Nome do recurso de imagem (drawable ou asset)
    val order: Int
)

