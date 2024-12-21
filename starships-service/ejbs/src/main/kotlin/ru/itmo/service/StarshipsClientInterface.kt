package ru.itmo.service

import jakarta.ejb.Remote
import jakarta.ws.rs.core.Response
import ru.itmo.service.model.SpaceMarinesServiceResponse
import java.util.*

@Remote
interface StarshipsClientInterface {
    fun saveStarship(starshipId: UUID, starshipName: String): SpaceMarinesServiceResponse
    fun loadSpaceMarine(starshipId: UUID, spaceMarineId: Long): SpaceMarinesServiceResponse
}