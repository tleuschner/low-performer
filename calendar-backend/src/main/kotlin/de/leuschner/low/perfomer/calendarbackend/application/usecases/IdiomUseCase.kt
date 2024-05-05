package de.leuschner.low.perfomer.calendarbackend.application.usecases

import de.leuschner.low.perfomer.calendarbackend.domain.entities.Idiom
import de.leuschner.low.perfomer.calendarbackend.domain.services.CreateIdiomCommand
import java.util.*

interface IdiomUseCase {
    fun findAllIdioms(): List<Idiom>
    fun findIdiomById(id: String): Optional<Idiom>
    fun findTodaysIdiom(): Idiom
    fun save(command: CreateIdiomCommand): Idiom
}