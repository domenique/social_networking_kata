package io.tripled.social.client.infrastructure

import io.tripled.social.client.domain.DateTimeProvider
import java.time.LocalDateTime

class SystemDateTimeProvider : DateTimeProvider {
    override fun now(): LocalDateTime {
        return LocalDateTime.now()
    }
}
