package ru.itmo.spacemarinesservice.service

import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import ru.itmo.spacemarinesservice.model.entity.AstartesCategory
import ru.itmo.spacemarinesservice.model.entity.QueryParams
import ru.itmo.spacemarinesservice.model.entity.SpaceMarine
import ru.itmo.spacemarinesservice.model.request.PostSpaceMarineRequest
import ru.itmo.spacemarinesservice.repository.SpaceMarinesRepository

@ApplicationScoped
class SpaceMarinesService {

    @Inject
    private lateinit var spaceMarinesRepository: SpaceMarinesRepository

    fun createSpaceMarine(postSpaceMarineRequest: PostSpaceMarineRequest): SpaceMarine {
        val spaceMarine = postSpaceMarineRequest.toSpaceMarineEntity()
        spaceMarinesRepository.saveSpaceMarine(spaceMarine)
        return spaceMarine
    }

    fun getSpaceMarines(
        queryParams: QueryParams,
    ): List<SpaceMarine> {
        return spaceMarinesRepository.getSpaceMarines(queryParams)
    }

    fun getSpaceMarine(
        spaceMarineId: Long
    ): SpaceMarine? = spaceMarinesRepository.getSpaceMarine(spaceMarineId)

    fun updateSpaceMarine(spaceMarineId: Long, postSpaceMarineRequest: PostSpaceMarineRequest) {
        spaceMarinesRepository.updateSpaceMarine(
            postSpaceMarineRequest.toSpaceMarineEntity(id = spaceMarineId),
        )
    }


    fun deleteSpaceMarineById(
        spaceMarineId: Long,
    ): Int = spaceMarinesRepository.deleteSpaceMarineById(spaceMarineId)

    fun deleteSpaceMarineByCategory(
        category: AstartesCategory,
    ): Int = spaceMarinesRepository.deleteSpaceMarineByCategory(category)

    fun getAnyLoyalSpaceMarine(): SpaceMarine? = spaceMarinesRepository.getAnyLoyalSpaceMarine()

    fun getAmountOfHealthySpaceMarines(minHealth: Float): Long = spaceMarinesRepository.getAmountOfHealthySpaceMarines(minHealth)
}