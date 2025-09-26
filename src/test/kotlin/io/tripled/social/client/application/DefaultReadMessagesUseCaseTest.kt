package io.tripled.social.client.application

import io.tripled.social.client.domain.DateTimeProvider
import io.tripled.social.client.infrastructure.TestSocialNetworkRepository
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test
import java.time.Duration

class DefaultReadMessagesUseCaseTest : AbstractUseCaseTest() {
    private var readMessagesUseCase: ReadMessagesUseCase? = null

    override fun initializeUseCase(
        socialNetworkRepository: TestSocialNetworkRepository,
        dateTimeProvider: DateTimeProvider
    ) {
        readMessagesUseCase = DefaultReadMessagesUseCase(socialNetworkRepository)
    }

    @Test
    fun canReadMessage() {
        saveMessage(createMessage("Alice", "I love the weather today"))
        fixateTimeWithOffset(Duration.ofMinutes(5))

        val message = readMessagesUseCase!!.readMessage("Alice")

        MatcherAssert.assertThat<String?>(
            message,
            Matchers.equalTo<String?>("I love the weather today (5 minutes ago)")
        )
    }
}