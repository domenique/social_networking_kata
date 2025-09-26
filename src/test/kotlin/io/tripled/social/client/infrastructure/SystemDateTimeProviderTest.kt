package io.tripled.social.client.infrastructure

import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

internal class SystemDateTimeProviderTest {
    private var dateTimeProvider: SystemDateTimeProvider? = null

    @BeforeEach
    fun setUp() {
        dateTimeProvider = SystemDateTimeProvider()
    }

    @Test
    fun returnsNow() {
        MatcherAssert.assertThat<LocalDateTime>(dateTimeProvider!!.now(), CoreMatchers.notNullValue())
    }
}