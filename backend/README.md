# Route Manager Backend

## TODO

* Data model
* Import data to OpenSearch/Elasticsearch
  * Sync data
* API for managing routes
* CI/CD (Target Platform?)

* Features
    * Import routes from Strava
    * \[Maybe] Import activities from Strava for more route suggestions
    * Add categories and/or tags to routes
    * Search/Filter routes/activities by
      * name
      * category
      * tag
      * location / country
      * type (1 for ride, 2 for runs)
      * sub type (1 for road, 2 for mountain bike, 3 for cross, 4 for trail, 5 for mixed)
      * distance / elevation gain / estimated moving time
      * free text (searches name, description etc.)
      * created at 
      * updated at
      * starred (boolean)
      * number of segments
      * ...
    * Sort by distance / elevation gain / estimated moving time etc.
    * Link to route/activity in Strava
    * \[Maybe] Export Route GPX/TCX
    * Calendar / Plan: Schedule routes
      * Weather information
      * Sunrise / Sunset
      * Outfit recommendation (Radklamottenguide)
    * Integrate TrainingPeaks? --> Assign route to workout / route suggestions
    * Integrate Best Bike Split?
    * Export to Google Maps? (View route in Google Maps)
    * List POIs on route (e.g. gas station @ KM 32)
    * Show Events of Strava Clubs (Today's events, Events this week, Calendar)

## Strava Links and Docs

* My API application: https://www.strava.com/settings/api
* API Docs: https://developers.strava.com/docs/
* API Getting Started: https://developers.strava.com/docs/getting-started/
* API Reference: https://developers.strava.com/docs/reference/
* Authentication: https://developers.strava.com/docs/authentication/

## Develop

### Build

```
./gradlew build
```

### Test

```
./gradlew test
```

### Run

```
docker-compose up -d
./gradlew bootRun
```

### Check for dependency updates

```
./gradlew dependencyUpdates
```

### Help

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.7.4/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.7.4/gradle-plugin/reference/html/#build-image)
* [Coroutines section of the Spring Framework Documentation](https://docs.spring.io/spring/docs/5.3.23/spring-framework-reference/languages.html#coroutines)
* [Spring Reactive Web](https://docs.spring.io/spring-boot/docs/2.7.4/reference/htmlsingle/#web.reactive)
* [Building a Reactive RESTful Web Service](https://spring.io/guides/gs/reactive-rest-service/)
