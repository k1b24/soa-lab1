package ru.itmo.spacemarinesservice.model.entity

import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "starships")
data class Starship(
    @Id
    val id: UUID,
    val name: String,
)
