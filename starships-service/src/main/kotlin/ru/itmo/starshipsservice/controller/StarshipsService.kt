package ru.itmo.starshipsservice.controller

//import jakarta.ws.rs.POST
//import jakarta.ws.rs.Path
//import jakarta.ws.rs.PathParam
//import jakarta.ws.rs.client.ClientBuilder
//import jakarta.ws.rs.client.Entity
//import jakarta.ws.rs.core.Response
import jakarta.jws.WebMethod
import jakarta.jws.WebService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.postForEntity

//@Path("/starships")
@WebService(serviceName = "StarshipsService")
@Controller
class StarshipsService(
    @Autowired private val restTemplate: RestTemplate,
) {

    @WebMethod
    fun saveStarship(
        starshipId: String,
        starshipName: String,
    ) {
        restTemplate.postForLocation("http://localhost:8080/space-marines-service/starships/create/${starshipId}/${starshipName}", null)
//        val client = ClientBuilder.newBuilder().hostnameVerifier { hostname, session -> true }.build()
//        client
//            .target("$SPACE_MARINES_SERVICE_URL/starships/create/$starshipId/$starshipName")
//            .request()
//            .post(Entity.text(""))
    }

    @WebMethod
    fun loadSpaceMarine(
         starshipId: String,
         spaceMarineId: Long,
    ) {
        restTemplate.postForLocation("http://localhost:8080/space-marines-service/starships/${starshipId}/load/${spaceMarineId}", null)
//        val client = ClientBuilder.newBuilder().hostnameVerifier { hostname, session -> true }.build()
//        client
//            .target("$SPACE_MARINES_SERVICE_URL/starships/$starshipId/load/$spaceMarineId")
//            .request()
//            .post(Entity.text(""))
    }
}