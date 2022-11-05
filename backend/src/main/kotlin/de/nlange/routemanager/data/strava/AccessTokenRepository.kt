package de.nlange.routemanager.data.strava

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Mono
import java.time.LocalDateTime

interface AccessTokenRepository : ReactiveCrudRepository<AccessTokenEntity, Int> {
    fun findByAthleteId(athleteId: Long): Mono<AccessTokenEntity>
}

@Table(name = "access_tokens")
class AccessTokenEntity(
    @Id
    val athleteId: Long,
    val accessToken: String,
    val refreshToken: String,
    val expiresAt: LocalDateTime
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as AccessTokenEntity
        if (athleteId != other.athleteId) return false
        return true
    }

    override fun hashCode(): Int {
        return athleteId.hashCode()
    }
}
