package de.leuschner.low.perfomer.calendarbackend.domain.repositories

import java.util.*

data class InsertIdiomCommand(
    val content: String,
    val authorId: UUID
)
