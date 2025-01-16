package ru.itmo.starshipsservice

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication


@SpringBootApplication
open class HelloApplication

fun main(args: Array<String>) {
    SpringApplication.run(HelloApplication::class.java, *args)
}