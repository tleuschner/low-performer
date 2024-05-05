package de.leuschner.low.perfomer.calendarbackend.domain.repositories

import de.leuschner.low.perfomer.calendarbackend.domain.entities.Author
import org.springframework.stereotype.Service
import java.util.*

@Service
class AuthorRepositoryImpl(val authorCrudRepository: AuthorCrudRepository) : AuthorRepository {
    override fun save(authorCommand: InsertAuthorCommand): Author =
        RepositoryConverter.toAuthor(authorCrudRepository.save(RepositoryConverter.toAuthorEntity(authorCommand)))

    override fun getAuthorById(uuid: UUID): Optional<Author> =
        authorCrudRepository.findById(uuid.toString()).map { RepositoryConverter.toAuthor(it) }

    override fun listAuthors(): List<Author> =
        authorCrudRepository.findAll().map { RepositoryConverter.toAuthor(it) }

}
