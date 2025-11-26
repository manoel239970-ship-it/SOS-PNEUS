package com.example.sospneus.data.mapper

import com.example.sospneus.data.entity.FavoriteContactEntity
import com.example.sospneus.data.model.FavoriteContact

/**
 * Mapper para converter entre Entity e Model do FavoriteContact
 */
object FavoriteContactMapper {
    
    fun toModel(entity: FavoriteContactEntity): FavoriteContact {
        return FavoriteContact(
            id = entity.id,
            name = entity.name,
            phone = entity.phone,
            address = entity.address,
            latitude = entity.latitude,
            longitude = entity.longitude,
            notes = entity.notes
        )
    }
    
    fun toEntity(model: FavoriteContact): FavoriteContactEntity {
        return FavoriteContactEntity(
            id = model.id,
            name = model.name,
            phone = model.phone,
            address = model.address,
            latitude = model.latitude,
            longitude = model.longitude,
            notes = model.notes
        )
    }
    
    fun toModelList(entities: List<FavoriteContactEntity>): List<FavoriteContact> {
        return entities.map { toModel(it) }
    }
    
    fun toEntityList(models: List<FavoriteContact>): List<FavoriteContactEntity> {
        return models.map { toEntity(it) }
    }
}

