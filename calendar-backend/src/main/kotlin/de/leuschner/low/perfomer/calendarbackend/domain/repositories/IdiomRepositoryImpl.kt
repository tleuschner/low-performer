package de.leuschner.low.perfomer.calendarbackend.domain.repositories

import de.leuschner.low.perfomer.calendarbackend.domain.entities.Idiom
import org.springframework.stereotype.Service
import java.util.*

@Service
class IdiomRepositoryImpl(
    val idiomCrudRepository: IdiomCrudRepository,
    val authorCrudRepository: AuthorCrudRepository
) : IdiomRepository {

    //    TODO Converter implementieren
    override fun findIdiomById(id: String): Optional<Idiom> = idiomCrudRepository.findById(id).map {
        RepositoryConverter.toIdiom(it)
    }

    override fun save(insertIdiomCommand: InsertIdiomCommand): Idiom = RepositoryConverter.toIdiom(
        idiomCrudRepository.save(
            RepositoryConverter.toIdiomEntity(
                insertIdiomCommand,
                authorCrudRepository
            )
        )
    )


    override fun findAllIdioms(): List<Idiom> = idiomCrudRepository.findAll().map { RepositoryConverter.toIdiom(it) }

}