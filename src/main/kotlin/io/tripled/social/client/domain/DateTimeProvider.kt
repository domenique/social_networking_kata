package io.tripled.social.client.domain

import java.time.LocalDateTime

interface DateTimeProvider {
    fun now(): LocalDateTime
}