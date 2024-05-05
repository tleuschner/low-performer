package de.leuschner.low.perfomer.calendarbackend.infrastructure.persistence

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "authors")
data class AuthorEntity(
    @Id @GeneratedValue(strategy = GenerationType.UUID) var id: String? = null,
    val name: String,
    var created: LocalDateTime
)