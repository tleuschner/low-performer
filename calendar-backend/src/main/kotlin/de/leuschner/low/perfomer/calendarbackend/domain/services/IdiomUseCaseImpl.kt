package de.leuschner.low.perfomer.calendarbackend.domain.services

import de.leuschner.low.perfomer.calendarbackend.application.usecases.IdiomUseCase
import de.leuschner.low.perfomer.calendarbackend.domain.entities.Idiom
import de.leuschner.low.perfomer.calendarbackend.domain.repositories.IdiomRepository
import de.leuschner.low.perfomer.calendarbackend.domain.repositories.UsedIdiomRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class IdiomUseCaseImpl(val idiomRepository: IdiomRepository, usedIdiomRepository: UsedIdiomRepository) : IdiomUseCase {
    override fun findIdiomById(id: String): Optional<Idiom> {
        return idiomRepository.findIdiomById(id)
    }

    override fun findAllIdioms(): List<Idiom> {
        return idiomRepository.findAllIdioms()
    }

    override fun findTodaysIdiom(): Idiom {
        val usedIdiom = usedIdiomRepository.findTodaysIdiom()
        return this.findIdiomById(usedIdiom.id)
    }

    override fun save(command: CreateIdiomCommand): Idiom {
        return idiomRepository.save(IdiomServiceConverter.toInserCommand(command))
    }

}