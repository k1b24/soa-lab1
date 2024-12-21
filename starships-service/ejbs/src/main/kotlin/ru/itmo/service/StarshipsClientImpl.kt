package ru.itmo.service

import jakarta.ejb.Stateless
import jakarta.ws.rs.client.ClientBuilder
import jakarta.ws.rs.client.Entity
import jakarta.ws.rs.core.Response
import org.jboss.ejb3.annotation.Pool
import ru.itmo.service.model.SpaceMarinesServiceResponse
import java.util.*

@Stateless
@Pool("my-ejb-pool")
class StarshipsClientImpl : StarshipsClientInterface {

    override fun saveStarship(starshipId: UUID, starshipName: String): SpaceMarinesServiceResponse {
        val client = ClientBuilder.newBuilder().hostnameVerifier { hostname, session -> true }.build()
        val response = client
            .target("$SPACE_MARINES_SERVICE_URL/starships/create/$starshipId/$starshipName")
            .request()
            .post(Entity.text(""))
        return SpaceMarinesServiceResponse(status = response.status)
    }

    override fun loadSpaceMarine(starshipId: UUID, spaceMarineId: Long): SpaceMarinesServiceResponse {
        val client = ClientBuilder.newBuilder().hostnameVerifier { hostname, session -> true }.build()
        val response = client
            .target("$SPACE_MARINES_SERVICE_URL/starships/$starshipId/load/$spaceMarineId")
            .request()
            .post(Entity.text(""))
        return SpaceMarinesServiceResponse(status = response.status)
    }

    companion object {
        private const val SPACE_MARINES_SERVICE_URL = "http://localhost:22001"
    }
}