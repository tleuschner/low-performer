package de.leuschner.low.perfomer.calendarbackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


// -> https://medium.com/idealo-tech-blog/hexagonal-ports-adapters-architecture-e3617bcf00a0
// -> https://github.com/tugcekonuklar/account-service/tree/master
// -> https://docs.emmet.io/cheat-sheet/
// TODO git it
// TODO sprüche auf ein kalenderdatum mappen (unique einmal pro jahr)
// TODO authentifizierung
// TODO gutes errorhandling - errors testem
// TODO refactoring - ports and adapters
// TODO linter ala detek
// TODO up und downvotes auf idioms
// TODO nutzer können vorschläge einreichen
// TODO nutzer können auf vorschläge voten
// TODO architekturdiagramm verschönern
// TODO deployment
// TODO gitlab pipeline
// TODO funktionieren die Testcontainer richtig (benutzen die tests WIRKLICH eine eigene datenbank??)
// TODO Mehr sprüche ausdenken/generieren

@SpringBootApplication
class CalendarBackendApplication

fun main(args: Array<String>) {
    runApplication<CalendarBackendApplication>(*args)
}