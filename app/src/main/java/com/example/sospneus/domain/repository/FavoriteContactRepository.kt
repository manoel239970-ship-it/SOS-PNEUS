package com.example.sospneus.domain.repository

import com.example.sospneus.data.model.FavoriteContact
import kotlinx.coroutines.flow.Flow

/**
 * Repository para gerenciar contatos favoritos
 */
interface FavoriteContactRepository {
    fun getAllContacts(): Flow<List<FavoriteContact>>
    suspend fun getContactById(id: Long): FavoriteContact?
    suspend fun insertContact(contact: FavoriteContact): Long
    suspend fun updateContact(contact: FavoriteContact)
    suspend fun deleteContact(contact: FavoriteContact)
    suspend fun deleteContactById(id: Long)
}

