package de.leuschner.low.perfomer.calendarbackend.domain.repositories

import de.leuschner.low.perfomer.calendarbackend.infrastructure.persistence.IdiomEntity
import org.springframework.data.repository.CrudRepository

interface IdiomCrudRepository : CrudRepository<IdiomEntity, String>
