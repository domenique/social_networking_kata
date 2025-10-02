package io.tripled.acceptance

import io.tripled.social.client.domain.DateTimeProvider
import java.time.*

class CucumberDateTimeProvider internal constructor(private var clock: Clock) : DateTimeProvider {
    override fun now(): LocalDateTime {
        return LocalDateTime.now(clock)
    }

    fun fixate(instant: Instant) {
        clock = Clock.fixed(instant, ZoneId.systemDefault())
    }

    fun fixateWithOffset(duration: Duration) {
        clock = Clock.offset(clock, duration)
    }
}
