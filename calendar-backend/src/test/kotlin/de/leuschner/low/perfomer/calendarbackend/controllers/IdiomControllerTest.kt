package de.leuschner.low.perfomer.calendarbackend.controllers

import de.leuschner.low.perfomer.calendarbackend.domain.entities.Idiom
import de.leuschner.low.perfomer.calendarbackend.domain.repositories.*
import de.leuschner.low.perfomer.calendarbackend.presentation.models.CreateIdiomRequest
import io.restassured.RestAssured
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import org.hamcrest.Matchers
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.HttpStatus
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.PostgreSQLContainer
import java.time.LocalDateTime
import java.util.*

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class IdiomControllerTest {
    companion object {
        @JvmStatic
        var postgres: PostgreSQLContainer<*> = PostgreSQLContainer(
            "postgres:15-alpine"
        )

        @JvmStatic
        @BeforeAll
        fun beforeAll() {
            postgres.start()
        }

        @JvmStatic
        @AfterAll
        fun afterAll() {
            postgres.stop()
        }

        @DynamicPropertySource
        fun configureProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url") { postgres.getJdbcUrl() }
            registry.add("spring.datasource.username") { postgres.username }
            registry.add("spring.datasource.password") { postgres.password }
        }
    }

    @Autowired
    lateinit var idiomRepository: IdiomRepository

    @Autowired
    lateinit var idiomCrudRepository: IdiomCrudRepository

    @Autowired
    lateinit var authorRepository: AuthorRepository

    @Autowired
    lateinit var authorCrudRepository: AuthorCrudRepository

    @LocalServerPort
    private val port: Int? = null

    @BeforeEach
    fun setUp() {
        RestAssured.baseURI = "http://localhost:$port"
        idiomCrudRepository.deleteAll()
        authorCrudRepository.deleteAll()
    }


    @Test
    fun testGetIdiomById() {
        // Arrange
        val savedIdiom = saveIdiom("tim", "Du hast schon gelebt, entspann dich lieber!")

        // Act
        given()
            .`when`()
            .get("/idioms/${savedIdiom.id}")
            .then()
            .statusCode(HttpStatus.OK.value())
            .body("content", Matchers.equalTo("Du hast schon gelebt, entspann dich lieber!"))
    }

    @Test
    fun testSaveIdiom() {
        // Arrange
        val insertAuthorCommand = InsertAuthorCommand(name = "tim", created = LocalDateTime.now())
        val author = authorRepository.save(insertAuthorCommand)

        val body = CreateIdiomRequest(
            content = "Du hast schon gelebt, entspann dich lieber!",
            authorId = author.id
        )

        // Act
        given()
            .contentType(ContentType.JSON)
            .body(body)
            .`when`()
            .post("/idioms")
            .then()
            // Assert
            .statusCode(HttpStatus.CREATED.value())
            .body("content", Matchers.equalTo("Du hast schon gelebt, entspann dich lieber!"))
    }

    @Test
    fun getTodaysIdiom() {

        val savedIdiom = saveIdiom("tim", "Du hast schon gelebt, entspann dich lieber!")
        // Update in DB

        given().`when`()
            .get("/idioms/today")
            .then()
            .body("content", Matchers.equalTo("Du hast schon gelebt, entspann dich lieber!"))
//            .body("used", Matchers.equalTo(0))
//            .body("lastUsed", Matchers.equalTo(LocalDateTime.of(2024, 0, 5, 0, 5, 20, 34)))
            .statusCode(HttpStatus.OK.value())
    }

    fun saveIdiom(authorName: String, idiomContent: String): Idiom {
        val insertAuthorCommand = InsertAuthorCommand(name = authorName, created = LocalDateTime.now())
        val author = authorRepository.save(insertAuthorCommand)
        val saveIdiomCommand =
            InsertIdiomCommand(content = idiomContent, UUID.fromString(author.id))
        return idiomRepository.save(saveIdiomCommand)
    }

}