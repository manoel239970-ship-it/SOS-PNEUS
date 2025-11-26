package com.example.sospneus.data.repository

import com.example.sospneus.data.dao.TutorialStepDao
import com.example.sospneus.data.mapper.TutorialStepMapper
import com.example.sospneus.data.model.TutorialStep
import com.example.sospneus.domain.repository.TutorialRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Implementação do TutorialRepository
 */
class TutorialRepositoryImpl(
    private val tutorialStepDao: TutorialStepDao
) : TutorialRepository {
    
    override fun getAllSteps(): Flow<List<TutorialStep>> {
        return tutorialStepDao.getAllSteps().map { entities ->
            TutorialStepMapper.toModelList(entities)
        }
    }
    
    override suspend fun getStepById(id: Int): TutorialStep? {
        return tutorialStepDao.getStepById(id)?.let { entity ->
            TutorialStepMapper.toModel(entity)
        }
    }
    
    override suspend fun insertStep(step: TutorialStep) {
        tutorialStepDao.insertStep(TutorialStepMapper.toEntity(step))
    }
    
    override suspend fun insertAllSteps(steps: List<TutorialStep>) {
        tutorialStepDao.insertAllSteps(TutorialStepMapper.toEntityList(steps))
    }
    
    override suspend fun loadDefaultSteps(): List<TutorialStep> {
        // Passos padrão do tutorial (offline-first)
        return listOf(
            TutorialStep(
                id = 1,
                title = "1. Estacione em local seguro",
                description = "Encontre um local plano e seguro, longe do tráfego. Ative o pisca-alerta e coloque o triângulo de sinalização.",
                imageResource = "step_1",
                order = 1
            ),
            TutorialStep(
                id = 2,
                title = "2. Prepare as ferramentas",
                description = "Pegue o macaco, a chave de roda e o estepe. Certifique-se de que o estepe está em boas condições.",
                imageResource = "step_2",
                order = 2
            ),
            TutorialStep(
                id = 3,
                title = "3. Afrouxe os parafusos",
                description = "Antes de levantar o carro, afrouxe ligeiramente os parafusos da roda furada usando a chave de roda.",
                imageResource = "step_3",
                order = 3
            ),
            TutorialStep(
                id = 4,
                title = "4. Posicione o macaco",
                description = "Coloque o macaco no ponto de apoio indicado no manual do veículo. NUNCA coloque embaixo de partes plásticas ou frágeis.",
                imageResource = "step_4",
                order = 4
            ),
            TutorialStep(
                id = 5,
                title = "5. Levante o veículo",
                description = "Levante o carro até que a roda furada fique completamente fora do chão. Não coloque partes do corpo embaixo do veículo.",
                imageResource = "step_5",
                order = 5
            ),
            TutorialStep(
                id = 6,
                title = "6. Remova a roda furada",
                description = "Retire completamente os parafusos e remova a roda furada. Coloque-a de lado com cuidado.",
                imageResource = "step_6",
                order = 6
            ),
            TutorialStep(
                id = 7,
                title = "7. Instale o estepe",
                description = "Coloque o estepe no lugar, alinhando os furos. Comece a apertar os parafusos manualmente.",
                imageResource = "step_7",
                order = 7
            ),
            TutorialStep(
                id = 8,
                title = "8. Aperte os parafusos",
                description = "Aperte os parafusos em formato de estrela (cruz) para distribuir a pressão uniformemente. Não aperte totalmente ainda.",
                imageResource = "step_8",
                order = 8
            ),
            TutorialStep(
                id = 9,
                title = "9. Abaixe o veículo",
                description = "Abaixe o carro completamente e remova o macaco. Agora aperte os parafusos com força total usando a chave de roda.",
                imageResource = "step_9",
                order = 9
            ),
            TutorialStep(
                id = 10,
                title = "10. Guarde tudo e vá ao borracheiro",
                description = "Guarde a roda furada, o macaco e as ferramentas. Dirija com cuidado até um borracheiro para reparar ou trocar o pneu definitivamente.",
                imageResource = "step_10",
                order = 10
            )
        )
    }
}

