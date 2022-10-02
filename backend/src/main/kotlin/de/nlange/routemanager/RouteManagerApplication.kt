package de.nlange.routemanager

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RouteManagerApplication

fun main(args: Array<String>) {
    runApplication<RouteManagerApplication>(*args)
}
