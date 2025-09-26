package io.tripled.social.client.application

import io.tripled.social.client.domain.DateTimeProvider
import io.tripled.social.client.infrastructure.TestSocialNetworkRepository
import org.junit.jupiter.api.Test

class DefaultPostMessageUseCaseTest : AbstractUseCaseTest() {
    private var postMessageUseCase: PostMessageUseCase? = null

    override fun initializeUseCase(
        socialNetworkRepository: TestSocialNetworkRepository,
        dateTimeProvider: DateTimeProvider
    ) {
        postMessageUseCase = DefaultPostMessageUseCase(socialNetworkRepository, dateTimeProvider)
    }

    @Test
    fun containsZeroMessages() {
        assertMessages()
    }

    @Test
    fun userCanPostMessage() {
        postMessageUseCase!!.postMessage("Alice", "Hi There!")

        assertMessages(createMessage("Alice", "Hi There!"))
    }

    @Test
    fun userCanPostMultipleMessages() {
        val firstMessage = createMessage("Alice", "Hi There!")
        val secondMessage = createMessage("Alice", "Hi there again!")

        postMessageUseCase!!.postMessage("Alice", "Hi There!")
        postMessageUseCase!!.postMessage("Alice", "Hi there again!")

        assertMessages(firstMessage, secondMessage)
    }

    @Test
    fun multipleUsersCanPostAMessage() {
        val firstMessage = createMessage("Alice", "Hi There!")
        val secondMessage = createMessage("Bob", "Hi there from Bob!")

        postMessageUseCase!!.postMessage("Alice", "Hi There!")
        postMessageUseCase!!.postMessage("Bob", "Hi there from Bob!")

        assertMessages(firstMessage, secondMessage)
    }
}