package de.leuschner.low.perfomer.calendarbackend.presentation.controllers

import de.leuschner.low.perfomer.calendarbackend.application.usecases.IdiomUseCase
import de.leuschner.low.perfomer.calendarbackend.presentation.models.CreateIdiomRequest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
class IdiomController(val idiomUseCase: IdiomUseCase) {
    @GetMapping("/idioms")
    fun getIdioms() = idiomUseCase.findAllIdioms()

    @GetMapping("/idioms/today")
    fun getTodaysIdiom() = idiomUseCase.findTodaysIdiom()

    @GetMapping("/idioms/{id}")
    fun getIdiomsById(@PathVariable id: String) = idiomUseCase.findIdiomById(id)

    @PostMapping("/idioms")
    @ResponseStatus(HttpStatus.CREATED)
    fun save(
        @RequestBody request: CreateIdiomRequest
    ) = idiomUseCase.save(IdiomApiConverter.toCreateIdiomCommand(request))
}