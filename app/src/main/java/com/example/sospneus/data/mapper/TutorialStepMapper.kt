package com.example.sospneus.data.mapper

import com.example.sospneus.data.entity.TutorialStepEntity
import com.example.sospneus.data.model.TutorialStep

/**
 * Mapper para converter entre Entity e Model do TutorialStep
 */
object TutorialStepMapper {
    
    fun toModel(entity: TutorialStepEntity): TutorialStep {
        return TutorialStep(
            id = entity.id,
            title = entity.title,
            description = entity.description,
            imageResource = entity.imageResource,
            order = entity.order
        )
    }
    
    fun toEntity(model: TutorialStep): TutorialStepEntity {
        return TutorialStepEntity(
            id = model.id,
            title = model.title,
            description = model.description,
            imageResource = model.imageResource,
            order = model.order
        )
    }
    
    fun toModelList(entities: List<TutorialStepEntity>): List<TutorialStep> {
        return entities.map { toModel(it) }
    }
    
    fun toEntityList(models: List<TutorialStep>): List<TutorialStepEntity> {
        return models.map { toEntity(it) }
    }
}

