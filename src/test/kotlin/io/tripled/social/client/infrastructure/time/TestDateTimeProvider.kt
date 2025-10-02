package io.tripled.social.client.infrastructure.time

import io.tripled.social.client.domain.DateTimeProvider
import java.time.Clock
import java.time.Duration
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

class TestDateTimeProvider(private var clock: Clock) : DateTimeProvider {
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