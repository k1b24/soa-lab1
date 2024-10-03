package ru.itmo.spacemarinesservice.model.entity

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.time.Instant
import java.time.LocalDate
import java.util.Date

@Entity
@Table(name = "spacemarines")
data class SpaceMarine(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val name: String,
    @Embedded
    val coordinates: Coordinates,
    @Column(name = "creation_date")
    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @JsonSerialize(`as` = LocalDate::class)
    val creationDate: LocalDate = LocalDate.now(),
    val health: Float,
    val loyal: Boolean,
    val height: Double,
    @Enumerated(EnumType.STRING)
    val category: AstartesCategory?,
    @Embedded
    val chapter: Chapter,
) {
    @Embeddable
    data class Coordinates(
        val x: Float,
        val y: Double,
    )

    @Embeddable
    data class Chapter(
        @Column(name = "chapter_name")
        val chapterName: String,
        @Column(name = "chapter_world")
        val chapterWorld: String?,
    )
}