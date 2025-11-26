package com.example.sospneus.data.model

/**
 * Modelo de dados para um contato favorito (borracheiro)
 */
data class FavoriteContact(
    val id: Long = 0,
    val name: String,
    val phone: String,
    val address: String? = null,
    val latitude: Double? = null,
    val longitude: Double? = null,
    val notes: String? = null
)

