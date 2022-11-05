package de.nlange.routemanager.data

import org.flywaydb.core.Flyway
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "spring.flyway")
class FlywayConfiguration() {

    lateinit var url: String
    lateinit var user: String
    lateinit var password: String

    @Bean(initMethod = "migrate")
    fun flyway(): Flyway {
        return Flyway(Flyway.configure().dataSource(url, user, password))
    }
}
