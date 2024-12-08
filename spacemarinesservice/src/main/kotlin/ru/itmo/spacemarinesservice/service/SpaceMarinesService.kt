package ru.itmo.spacemarinesservice.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import ru.itmo.spacemarinesservice.model.body.AstartesCategory
import ru.itmo.spacemarinesservice.model.body.QueryParams
import ru.itmo.spacemarinesservice.model.body.SpaceMarine
import ru.itmo.spacemarinesservice.model.request.PostSpaceMarineRequest
import ru.itmo.spacemarinesservice.repository.SpaceMarinesRepository

@Component
class SpaceMarinesService {

    @Autowired
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