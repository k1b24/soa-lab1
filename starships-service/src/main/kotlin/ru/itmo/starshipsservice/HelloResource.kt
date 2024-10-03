package ru.itmo.starshipsservice

import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces

@Path("/hello-world")
class HelloResource {
    @GET
    @Produces("text/plain")
    fun hello(): String {
        return "Hello, World!"
    }
}