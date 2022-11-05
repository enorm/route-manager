package de.nlange.routemanager.data.strava

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.time.Instant

// https://developers.strava.com/docs/reference/

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class AccessToken(
    val tokenType: String,
    @JsonFormat(shape = JsonFormat.Shape.NUMBER, pattern = "s")
    val expiresAt: Instant,
    val expiresIn: Int,
    val refreshToken: String,
    val accessToken: String,
    val athlete: SummaryAthlete
)

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class RefreshedAccessToken(
    val tokenType: String,
    @JsonFormat(shape = JsonFormat.Shape.NUMBER, pattern = "s")
    val expiresAt: Instant,
    val expiresIn: Int,
    val refreshToken: String,
    val accessToken: String
)

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class DetailedAthlete(
    val id: Long,
    val username: String,
    val firstname: String,
    val lastname: String,
    val sex: String,
    val city: String,
    val country: String,
    val profile: String,
    val profileMedium: String,
    val bio: String,
    val weight: Double,
    val badgeTypeID: Long,
    val premium: Boolean,
    val summit: Boolean,
    val createdAt: String,
    val updatedAt: String,
    val resourceState: Long,
    val state: String
)

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class Route(
    val id: Long,
    val idStr: String,
    val name: String,
    val type: Long,
    val subType: Long,
    val description: String,
    val starred: Boolean,
    val private: Boolean,
    val athlete: SummaryAthlete,
    val distance: Double,
    val elevationGain: Double,
    val estimatedMovingTime: Long,
    val segments: List<SummarySegment>?,
    val map: PolylineMap,
    val mapUrls: MapUrls,
    val timestamp: Long,
    val createdAt: String,
    val updatedAt: String,
    val resourceState: Int
)

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class MapUrls(
    val url: String,
    val retinaUrl: String
)

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class SummaryAthlete(
    val id: Long,
    val username: String,
    val firstname: String,
    val lastname: String,
    val sex: String,
    val profile: String,
    val profileMedium: String,
    val city: String,
    val state: String,
    val country: String,
    val premium: Boolean,
    val summit: Boolean,
    val createdAt: String,
    val updatedAt: String,
    val badgeTypeId: Long,
    val resourceState: Int,
)

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class PolylineMap(
    val id: String,
    val summaryPolyline: String,
    val polyline: String?
)

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class SummarySegment(
    val id: Long,
    val name: String,
    val activityType: String,
    val state: String,
    val private: Boolean,
    val country: String,
    val city: String,
    val distance: Double,
    val averageGrade: Double,
    val maximumGrade: Double,
    val climbCategory: Long,
    val elevationLow: Double,
    val elevationHigh: Double,
    val startLatlng: String,
    val endLatlng: String,
    val athletePREffort: SummarySegmentEffort,
    val athleteSegmentStats: SummaryPRSegmentEffort
)

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class SummarySegmentEffort(
    val id: Long,
    val distance: Double,
    val activityId: Long,
    val startDate: String,
    val startDateLocal: String,
    val elapsedTime: Long,
    val isKom: Boolean
)

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class SummaryPRSegmentEffort(
    val prActivityId: Long,
    val prDate: String,
    val prElapsedTime: Long,
    val effortCount: Long
)
