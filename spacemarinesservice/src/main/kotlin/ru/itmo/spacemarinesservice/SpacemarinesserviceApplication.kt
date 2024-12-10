package ru.itmo.spacemarinesservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan("ru.itmo.spacemarinesservice")
class SpacemarinesserviceApplication

fun main(args: Array<String>) {
	runApplication<SpacemarinesserviceApplication>(*args)
}
