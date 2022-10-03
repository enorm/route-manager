package de.nlange.routemanager.data.strava

import mu.KotlinLogging
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.ExchangeFilterFunction
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.LocalDateTime
import java.time.ZoneOffset

private val logger = KotlinLogging.logger {}

@Component
class StravaClient(
    private val stravaProperties: StravaProperties,
    private val accessTokenRepository: AccessTokenRepository
) {
    val webClient = WebClient.builder()
        .baseUrl("https://www.strava.com/api/v3")
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
        .filter(logRequest())
        .build()

    fun connect(authorizationCode: String): Mono<AccessTokenEntity> {
        return webClient.post().uri("/oauth/token")
            .body(
                BodyInserters.fromFormData("client_id", stravaProperties.clientId)
                    .with("client_secret", stravaProperties.clientSecret)
                    .with("code", authorizationCode)
                    .with("grant_type", "authorization_code")
            )
            .retrieve()
            .bodyToMono(AccessToken::class.java)
            .flatMap {
                logger.info { "Got access token: $it" }
                accessTokenRepository.save(
                    AccessTokenEntity(
                        athleteId = it.athlete.id,
                        accessToken = it.accessToken,
                        refreshToken = it.refreshToken,
                        expiresAt = LocalDateTime.ofInstant(it.expiresAt, ZoneOffset.UTC)
                    )
                )
            }
    }

    fun getAthlete(athleteId: Long): Mono<DetailedAthlete> {
        return getAccessToken(athleteId).flatMap {
            webClient.get().uri("/athlete")
                .header("Authorization", "Bearer ${it.accessToken}")
                .retrieve()
                .bodyToMono(DetailedAthlete::class.java)
        }
    }

    // TODO: Paging
    fun getRoutes(athleteId: Long): Flux<Route> {
        return getAccessToken(athleteId).flatMapMany {
            webClient.get().uri("/athletes/$athleteId/routes")
                .header("Authorization", "Bearer ${it.accessToken}")
                .retrieve()
                .bodyToFlux(Route::class.java)
        }
    }

    private fun getAccessToken(athleteId: Long): Mono<AccessTokenEntity> {
        return accessTokenRepository.findByAthleteId(athleteId).flatMap {
            if (it.expiresAt.plusSeconds(10).isBefore(LocalDateTime.now(ZoneOffset.UTC))) {
                Mono.just(it)
            } else {
                refreshToken(athleteId, it.refreshToken)
            }
        }
    }

    private fun refreshToken(athleteId: Long, refreshToken: String): Mono<AccessTokenEntity> {
        return webClient.post().uri("/oauth/token")
            .body(
                BodyInserters.fromFormData("client_id", stravaProperties.clientId)
                    .with("client_secret", stravaProperties.clientSecret)
                    .with("refresh_token", refreshToken)
                    .with("grant_type", "refresh_token")
            )
            .retrieve()
            .bodyToMono(RefreshedAccessToken::class.java)
            .flatMap {
                logger.info { "Got new access token: $it" }
                accessTokenRepository.save(
                    AccessTokenEntity(
                        athleteId = athleteId,
                        accessToken = it.accessToken,
                        refreshToken = it.refreshToken,
                        expiresAt = LocalDateTime.ofInstant(it.expiresAt, ZoneOffset.UTC)
                    )
                )
            }
    }

    private fun logRequest(): ExchangeFilterFunction {
        return ExchangeFilterFunction.ofRequestProcessor { clientRequest ->
            logger.info { "${clientRequest.method()} ${clientRequest.url()}" }
            Mono.just(clientRequest)
        }
    }
}
