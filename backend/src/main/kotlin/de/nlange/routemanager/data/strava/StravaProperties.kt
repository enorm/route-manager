package de.nlange.routemanager.data.strava

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "strava")
class StravaProperties {
    lateinit var clientId: String
    lateinit var clientSecret: String
}
