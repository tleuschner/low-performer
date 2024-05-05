package de.leuschner.low.perfomer.calendarbackend.domain.repositories

import de.leuschner.low.perfomer.calendarbackend.domain.entities.Author
import de.leuschner.low.perfomer.calendarbackend.domain.entities.Idiom
import de.leuschner.low.perfomer.calendarbackend.infrastructure.persistence.AuthorEntity
import de.leuschner.low.perfomer.calendarbackend.infrastructure.persistence.IdiomEntity
import java.time.LocalDateTime

class RepositoryConverter {
    companion object {
        fun toAuthorEntity(command: InsertAuthorCommand) = AuthorEntity(name = command.name, created = command.created)

        fun toAuthor(authorEntity: AuthorEntity): Author {
//            TODO: geht das auch schöner?
            if (authorEntity.id.isNullOrEmpty()) throw Exception("No author id, this shouldn't happen glaube ich, sonst mal debuggen")

            return Author(id = authorEntity.id!!, name = authorEntity.name)
        }

        fun toIdiomEntity(
            insertIdiomCommand: InsertIdiomCommand,
            authorCrudRepository: AuthorCrudRepository
        ): IdiomEntity {
            val author = authorCrudRepository.findById(insertIdiomCommand.authorId.toString())

            if (author.isEmpty) {
                throw Exception("No author found with id ${insertIdiomCommand.authorId}")
            }

            return IdiomEntity(
                content = insertIdiomCommand.content,
                updated = LocalDateTime.now(),
                created = LocalDateTime.now(),
                downvotes = 0,
                upvotes = 0,
                author = author.get()
            )
        }

        fun toIdiom(idiomEntity: IdiomEntity): Idiom {

            if (idiomEntity.id.isNullOrEmpty()) throw Exception("No idiom id, this shouldn't happen glaube ich, sonst mal debuggen")

            return Idiom(
                id = idiomEntity.id!!,
                content = idiomEntity.content,
                upvotes = idiomEntity.upvotes,
                downvotes = idiomEntity.downvotes,
                created = idiomEntity.created,
                updated = idiomEntity.updated,
                author = this.toAuthor(idiomEntity.author)
            )
        }

    }
}