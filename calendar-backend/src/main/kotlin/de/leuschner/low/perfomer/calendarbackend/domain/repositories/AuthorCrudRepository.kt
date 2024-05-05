package de.leuschner.low.perfomer.calendarbackend.domain.repositories;

import de.leuschner.low.perfomer.calendarbackend.infrastructure.persistence.AuthorEntity
import org.springframework.data.repository.CrudRepository

interface AuthorCrudRepository : CrudRepository<AuthorEntity, String>

