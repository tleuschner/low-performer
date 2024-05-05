package de.leuschner.low.perfomer.calendarbackend.domain.entities

import java.time.LocalDateTime

data class Idiom(
    var id: String,
    val content: String,
    val created: LocalDateTime?,
    val updated: LocalDateTime?,
    val upvotes: Int,
    val downvotes: Int,
    var author: Author,
)
