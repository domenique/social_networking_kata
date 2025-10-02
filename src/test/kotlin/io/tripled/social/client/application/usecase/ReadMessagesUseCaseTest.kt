package io.tripled.social.client.application.usecase

import io.tripled.social.client.application.ReadMessages
import io.tripled.social.client.domain.DateTimeProvider
import io.tripled.social.client.infrastructure.inmemory.TestSocialNetworkRepository
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test
import java.time.Duration

class ReadMessagesUseCaseTest : AbstractUseCaseTest() {
    private var readMessages: ReadMessages? = null

    override fun initializeUseCase(
        socialNetworkRepository: TestSocialNetworkRepository,
        dateTimeProvider: DateTimeProvider
    ) {
        readMessages = ReadMessagesUseCase(socialNetworkRepository)
    }

    @Test
    fun canReadMessage() {
        saveMessage(createMessage("Alice", "I love the weather today"))
        fixateTimeWithOffset(Duration.ofMinutes(5))

        val message = readMessages!!.readMessage("Alice")

        MatcherAssert.assertThat<String?>(
            message,
            Matchers.equalTo<String?>("I love the weather today (5 minutes ago)")
        )
    }
}