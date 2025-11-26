package com.example.sospneus.data.model

/**
 * Modelo de dados para localização GPS
 */
data class LocationData(
    val latitude: Double,
    val longitude: Double,
    val accuracy: Float = 0f,
    val timestamp: Long = System.currentTimeMillis()
)

