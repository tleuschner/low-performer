package de.leuschner.low.perfomer.calendarbackend.domain.services

import java.util.*

data class CreateIdiomCommand
    (val content: String, val authorId: UUID)
