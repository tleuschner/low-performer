package de.leuschner.low.perfomer.calendarbackend

data class IdiomDto (
    val content: String,
    val authorId: String,
    val upvotes: Int,
    val downvotes: Int
)