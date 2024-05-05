package de.leuschner.low.perfomer.calendarbackend.domain.services

import de.leuschner.low.perfomer.calendarbackend.domain.repositories.InsertIdiomCommand

class IdiomServiceConverter {
    companion object {
        fun toInserCommand(createIdiomCommand: CreateIdiomCommand) = InsertIdiomCommand(
            content = createIdiomCommand.content,
            authorId = createIdiomCommand.authorId
        )
    }
}