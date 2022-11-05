package de.nlange.routemanager.api

import de.nlange.routemanager.data.strava.AccessTokenEntity
import de.nlange.routemanager.data.strava.DetailedAthlete
import de.nlange.routemanager.data.strava.Route
import de.nlange.routemanager.data.strava.StravaClient
import de.nlange.routemanager.data.strava.StravaProperties
import mu.KotlinLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

private val logger = KotlinLogging.logger {}

@RestController
@RequestMapping("/strava")
class StravaRestController(private val stravaClient: StravaClient, private val stravaProperties: StravaProperties) {

    @GetMapping("/connect")
    fun connect(@RequestParam code: String, @RequestParam scope: String): Mono<AccessTokenEntity> {
        logger.info { "Connect to Strava with code $code and scope $scope" }
        return stravaClient.connect(authorizationCode = code)
    }

    @GetMapping("/athlete/{athleteId}")
    fun getAthlete(@PathVariable athleteId: Long): Mono<DetailedAthlete> {
        return stravaClient.getAthlete(athleteId)
    }

    @GetMapping("/athlete/{athleteId}/routes")
    fun getRoutes(@PathVariable athleteId: Long): Flux<Route> {
        return stravaClient.getRoutes(athleteId)
    }
}
