package com.krill

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager
import org.testcontainers.containers.PostgreSQLContainer

class PostgresResource : QuarkusTestResourceLifecycleManager {

    companion object {
        private val db: PostgreSQLContainer<*> = PostgreSQLContainer("postgres:13")
            .withDatabaseName("krilldb")
            .withUsername("user")
            .withPassword("password")
    }

    override fun start(): Map<String, String> {
        db.start()
        return mapOf(
            Pair("quarkus.datasource.jdbc.url", db.jdbcUrl)
        )
    }

    override fun stop() {
        db.stop()
    }
}