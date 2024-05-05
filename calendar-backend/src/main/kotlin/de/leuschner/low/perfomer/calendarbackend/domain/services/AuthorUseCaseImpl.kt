package de.leuschner.low.perfomer.calendarbackend.domain.services

import de.leuschner.low.perfomer.calendarbackend.application.usecases.AuthorUseCase
import de.leuschner.low.perfomer.calendarbackend.domain.entities.Author
import de.leuschner.low.perfomer.calendarbackend.domain.repositories.AuthorRepository
import org.springframework.stereotype.Service
import java.util.*
import kotlin.jvm.optionals.getOrElse

@Service
class AuthorUseCaseImpl(val authorRepository: AuthorRepository) : AuthorUseCase {

    override fun createAuthor(command: CreateAuthorCommand): Author =
        authorRepository.save(AuthorServiceConverter.toInsertCommand(command))

    override fun getAuthorById(id: UUID): Author =
//        TODO: throw and catch not found exception
        authorRepository.getAuthorById(id).getOrElse { throw Exception("not found") }

    override fun listAuthors(): List<Author> = authorRepository.listAuthors()


}