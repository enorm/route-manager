package de.nlange.routemanager

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories

@SpringBootApplication
@EnableR2dbcRepositories
class RouteManagerApplication

fun main(args: Array<String>) {
    runApplication<RouteManagerApplication>(*args)
}
