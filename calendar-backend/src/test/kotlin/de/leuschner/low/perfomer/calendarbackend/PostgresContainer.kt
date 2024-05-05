package de.leuschner.low.perfomer.calendarbackend

import org.testcontainers.containers.PostgreSQLContainer


class PostgresContainer private constructor() : PostgreSQLContainer<PostgresContainer?>(
    IMAGE_VERSION
) {
    override fun start() {
        super.start()
        System.setProperty("DB_URL", container!!.getJdbcUrl())
        System.setProperty("DB_USERNAME", container!!.username)
        System.setProperty("DB_PASSWORD", container!!.password)
    }

    override fun stop() {
        //do nothing, JVM handles shut down
    }

    companion object {
        private const val IMAGE_VERSION = "postgres:15-alpine"
        private var container: PostgresContainer? = null
        val instance: PostgresContainer?
            get() {
                if (container == null) {
                    container = PostgresContainer()
                }
                return container
            }
    }
}