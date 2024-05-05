package de.leuschner.low.perfomer.calendarbackend.infrastructure.persistence

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "idioms")
data class IdiomEntity(
    @Id @GeneratedValue(strategy = GenerationType.UUID) var id: String? = null,
    val content: String,
    val created: LocalDateTime?,
    val updated: LocalDateTime?,
    val upvotes: Int,
    val downvotes: Int,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    var author: AuthorEntity,
)