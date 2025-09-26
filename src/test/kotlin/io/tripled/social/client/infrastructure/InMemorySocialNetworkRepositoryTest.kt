package io.tripled.social.client.infrastructure

import io.tripled.social.client.domain.SocialNetwork
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.Clock

internal class InMemorySocialNetworkRepositoryTest {
    private var repository: InMemorySocialNetworkRepository? = null

    @BeforeEach
    fun setUp() {
        repository = InMemorySocialNetworkRepository(TestDateTimeProvider(Clock.systemDefaultZone()))
    }

    @Test
    fun alwaysReturnsSameInstance() {
        val socialNetwork = repository!!.get()

        MatcherAssert.assertThat<SocialNetwork?>(
            socialNetwork,
            Matchers.sameInstance<SocialNetwork>(repository!!.get())
        )
    }
}