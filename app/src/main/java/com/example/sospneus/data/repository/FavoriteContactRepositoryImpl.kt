package com.example.sospneus.data.repository

import com.example.sospneus.data.dao.FavoriteContactDao
import com.example.sospneus.data.mapper.FavoriteContactMapper
import com.example.sospneus.data.model.FavoriteContact
import com.example.sospneus.domain.repository.FavoriteContactRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Implementação do FavoriteContactRepository
 */
class FavoriteContactRepositoryImpl(
    private val favoriteContactDao: FavoriteContactDao
) : FavoriteContactRepository {
    
    override fun getAllContacts(): Flow<List<FavoriteContact>> {
        return favoriteContactDao.getAllContacts().map { entities ->
            FavoriteContactMapper.toModelList(entities)
        }
    }
    
    override suspend fun getContactById(id: Long): FavoriteContact? {
        return favoriteContactDao.getContactById(id)?.let { entity ->
            FavoriteContactMapper.toModel(entity)
        }
    }
    
    override suspend fun insertContact(contact: FavoriteContact): Long {
        return favoriteContactDao.insertContact(FavoriteContactMapper.toEntity(contact))
    }
    
    override suspend fun updateContact(contact: FavoriteContact) {
        favoriteContactDao.updateContact(FavoriteContactMapper.toEntity(contact))
    }
    
    override suspend fun deleteContact(contact: FavoriteContact) {
        favoriteContactDao.deleteContact(FavoriteContactMapper.toEntity(contact))
    }
    
    override suspend fun deleteContactById(id: Long) {
        favoriteContactDao.deleteContactById(id)
    }
}

