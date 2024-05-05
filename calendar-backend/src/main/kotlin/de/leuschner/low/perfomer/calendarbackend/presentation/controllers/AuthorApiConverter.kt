package de.leuschner.low.perfomer.calendarbackend.presentation.controllers

import de.leuschner.low.perfomer.calendarbackend.domain.entities.Author
import de.leuschner.low.perfomer.calendarbackend.domain.services.CreateAuthorCommand
import de.leuschner.low.perfomer.calendarbackend.presentation.models.AuthorResponse
import de.leuschner.low.perfomer.calendarbackend.presentation.models.CreateAuthorRequest
import java.time.LocalDateTime


class AuthorApiConverter {
    companion object {
        fun toAuthorResponse(author: Author): AuthorResponse = AuthorResponse(id = author.id, name = author.name)

        fun toCreateAuthorCommand(request: CreateAuthorRequest) =
            CreateAuthorCommand(name = request.name, created = LocalDateTime.now())
    }
}