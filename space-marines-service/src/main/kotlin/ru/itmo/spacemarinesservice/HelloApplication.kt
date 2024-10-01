package ru.itmo.spacemarinesservice

import jakarta.ws.rs.ApplicationPath
import jakarta.ws.rs.core.Application

@ApplicationPath("/api")
class HelloApplication : Application() {

}