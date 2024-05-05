package de.leuschner.low.perfomer.calendarbackend.application.usecases

import de.leuschner.low.perfomer.calendarbackend.domain.entities.Author
import de.leuschner.low.perfomer.calendarbackend.domain.services.CreateAuthorCommand
import java.util.*

interface AuthorUseCase {
    fun listAuthors(): List<Author>
    fun getAuthorById(id: UUID): Author
    fun createAuthor(command: CreateAuthorCommand): Author
}