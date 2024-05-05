package de.leuschner.low.perfomer.calendarbackend.domain.services

import java.time.LocalDateTime

data class CreateAuthorCommand(
   val name: String,
   val created: LocalDateTime
)
