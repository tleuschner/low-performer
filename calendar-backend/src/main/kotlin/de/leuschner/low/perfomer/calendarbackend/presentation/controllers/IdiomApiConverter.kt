package de.leuschner.low.perfomer.calendarbackend.presentation.controllers

import de.leuschner.low.perfomer.calendarbackend.domain.services.CreateIdiomCommand
import de.leuschner.low.perfomer.calendarbackend.presentation.models.CreateIdiomRequest
import java.util.*

class IdiomApiConverter {
    companion object {
        fun toCreateIdiomCommand(request: CreateIdiomRequest) =
            CreateIdiomCommand(content = request.content, authorId = UUID.fromString(request.authorId))
    }
}