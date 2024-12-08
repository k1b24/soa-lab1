package ru.itmo.spacemarinesservice.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import ru.itmo.spacemarinesservice.model.body.Starship
import ru.itmo.spacemarinesservice.repository.SpaceMarinesRepository
import ru.itmo.spacemarinesservice.repository.StarshipsRepository
import java.util.UUID

@Component
class StarshipsService {

    @Autowired
    private lateinit var starshipsRepository: StarshipsRepository

    @Autowired
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