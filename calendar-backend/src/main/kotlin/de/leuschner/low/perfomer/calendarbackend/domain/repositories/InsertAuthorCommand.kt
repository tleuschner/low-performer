package de.leuschner.low.perfomer.calendarbackend.domain.repositories

import java.time.LocalDateTime

data class InsertAuthorCommand(
    val name: String,
    val created: LocalDateTime
)
