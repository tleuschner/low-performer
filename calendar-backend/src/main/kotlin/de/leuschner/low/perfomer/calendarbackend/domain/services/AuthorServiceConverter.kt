package de.leuschner.low.perfomer.calendarbackend.domain.services

import de.leuschner.low.perfomer.calendarbackend.domain.repositories.InsertAuthorCommand

class AuthorServiceConverter {
    companion object {
        fun toInsertCommand(createAuthorCommand: CreateAuthorCommand) =
            InsertAuthorCommand(name = createAuthorCommand.name, created = createAuthorCommand.created)
    }
}
