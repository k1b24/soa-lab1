package ru.itmo.spacemarinesservice.service

import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import ru.itmo.spacemarinesservice.model.entity.Starship
import ru.itmo.spacemarinesservice.repository.SpaceMarinesRepository
import ru.itmo.spacemarinesservice.repository.StarshipsRepository
import java.util.UUID

@ApplicationScoped
class StarshipsService {

    @Inject
    private lateinit var starshipsRepository: StarshipsRepository

    @Inject
    private lateinit var spaceMarinesRepository: SpaceMarinesRepository

    fun saveStarship(
        starshipId: UUID,
        starshipName: String,
    ) {
        val starship = Starship(
            id = starshipId,
            name = starshipName,
        )
        starshipsRepository.saveStarship(starship)
    }

    fun loadSpaceMarine(starshipId: UUID, spaceMarineId: Long): Int =
        spaceMarinesRepository.addToStarship(spaceMarineId, starshipId)
}