package de.leuschner.low.perfomer.calendarbackend.domain.repositories

import de.leuschner.low.perfomer.calendarbackend.domain.entities.Author
import java.util.*

interface AuthorRepository {
    fun save(insertAuthorCommand: InsertAuthorCommand): Author
    fun getAuthorById(uuid: UUID): Optional<Author>
    fun listAuthors(): List<Author>
}