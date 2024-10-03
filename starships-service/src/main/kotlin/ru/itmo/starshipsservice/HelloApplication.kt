package ru.itmo.starshipsservice

import jakarta.ws.rs.ApplicationPath
import jakarta.ws.rs.core.Application

@ApplicationPath("/api")
class HelloApplication : Application() {

}