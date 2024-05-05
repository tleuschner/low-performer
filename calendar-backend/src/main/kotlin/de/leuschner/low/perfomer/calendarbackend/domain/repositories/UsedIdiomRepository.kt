package de.leuschner.low.perfomer.calendarbackend.domain.repositories

import de.leuschner.low.perfomer.calendarbackend.domain.entities.UsedIdiom

interface UsedIdiomRepository {
    fun updateUsedIdiom(): Unit
    fun findTodaysIdiom(): UsedIdiom
}