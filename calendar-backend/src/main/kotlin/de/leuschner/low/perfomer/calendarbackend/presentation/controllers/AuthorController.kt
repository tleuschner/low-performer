package de.leuschner.low.perfomer.calendarbackend.presentation.controllers

import de.leuschner.low.perfomer.calendarbackend.application.usecases.AuthorUseCase
import de.leuschner.low.perfomer.calendarbackend.presentation.models.CreateAuthorRequest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class AuthorController(val authorService: AuthorUseCase) {
    @GetMapping("/authors")
    fun getAuthors() = authorService.listAuthors()

    @GetMapping("/authors/{id}")
    fun getAuthorById(@PathVariable id: String) = authorService.getAuthorById(UUID.fromString(id))

    @PostMapping("/authors")
    @ResponseStatus(HttpStatus.CREATED)
    fun save(@RequestBody request: CreateAuthorRequest) =
        AuthorApiConverter.toAuthorResponse(authorService.createAuthor(AuthorApiConverter.toCreateAuthorCommand(request)))
}