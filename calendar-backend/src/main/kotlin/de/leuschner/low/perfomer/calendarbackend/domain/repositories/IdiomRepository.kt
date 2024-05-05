package de.leuschner.low.perfomer.calendarbackend.domain.repositories

import de.leuschner.low.perfomer.calendarbackend.domain.entities.Idiom
import java.util.*

interface IdiomRepository {
    fun findAllIdioms(): List<Idiom>
    fun findIdiomById(id: String): Optional<Idiom>
    fun save(insertIdiomCommand: InsertIdiomCommand): Idiom
}