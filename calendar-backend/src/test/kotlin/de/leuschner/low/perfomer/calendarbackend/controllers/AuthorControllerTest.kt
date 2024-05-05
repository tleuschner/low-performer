package de.leuschner.low.perfomer.calendarbackend.controllers

import de.leuschner.low.perfomer.calendarbackend.domain.repositories.AuthorCrudRepository
import de.leuschner.low.perfomer.calendarbackend.domain.repositories.IdiomCrudRepository
import de.leuschner.low.perfomer.calendarbackend.domain.repositories.IdiomRepository
import de.leuschner.low.perfomer.calendarbackend.infrastructure.persistence.AuthorEntity
import de.leuschner.low.perfomer.calendarbackend.presentation.models.CreateAuthorRequest
import io.restassured.RestAssured
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.hasSize
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


// https://testcontainers.com/guides/testing-spring-boot-rest-api-using-testcontainers/
// https://www.baeldung.com/spring-boot-testcontainers-integration-test
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class AuthorControllerTest {
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
    lateinit var authorCrudRepository: AuthorCrudRepository

    @Autowired
    lateinit var idiomRepository: IdiomRepository

    @Autowired
    lateinit var idiomCrudRepository: IdiomCrudRepository

    @LocalServerPort
    private val port: Int? = null

    @BeforeEach
    fun setUp() {
        RestAssured.baseURI = "http://localhost:$port"
        idiomCrudRepository.deleteAll()
        authorCrudRepository.deleteAll()
    }

    @Test
    fun testCreateAuthor() {
        //  Arrange
        val authorRequest = CreateAuthorRequest(name = "Tim")

        // Act
        given()
            .contentType(ContentType.JSON)
            .body(authorRequest)
            .`when`()
            .post("/authors")
            .then()
            // Assert
            .statusCode(HttpStatus.CREATED.value())
            .body("name", equalTo("Tim"))
    }


    @Test
    fun testGetAuthorById() {
        // Arrange
        val authorEntity = authorCrudRepository.save(AuthorEntity(null, "testAutor", LocalDateTime.now()))

        // Act
        given()
            .contentType(ContentType.JSON)
            .`when`()
            .get("/authors/${authorEntity.id}")
            .then()
            .statusCode(HttpStatus.OK.value())
            .body("name", equalTo("testAutor"))
    }

    @Test
    fun testGetAllAuthors() {
        authorCrudRepository.save(AuthorEntity(null, "testAutor1", LocalDateTime.now()))
        authorCrudRepository.save(AuthorEntity(null, "testAutor2", LocalDateTime.now()))

        given()
            .contentType(ContentType.JSON)
            .`when`()
            .get("/authors")
            .then()
            .statusCode(HttpStatus.OK.value())
            .body("", hasSize<List<AuthorEntity>>(2))
    }
}