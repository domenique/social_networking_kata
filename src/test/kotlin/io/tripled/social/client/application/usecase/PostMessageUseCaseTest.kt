package io.tripled.social.client.application.usecase

import io.tripled.social.client.application.PostMessage
import io.tripled.social.client.domain.DateTimeProvider
import io.tripled.social.client.infrastructure.inmemory.TestSocialNetworkRepository
import org.junit.jupiter.api.Test

class PostMessageUseCaseTest : AbstractUseCaseTest() {
    private var postMessage: PostMessage? = null

    override fun initializeUseCase(
        socialNetworkRepository: TestSocialNetworkRepository,
        dateTimeProvider: DateTimeProvider
    ) {
        postMessage = PostMessageUseCase(socialNetworkRepository, dateTimeProvider)
    }

    @Test
    fun containsZeroMessages() {
        assertMessages()
    }

    @Test
    fun userCanPostMessage() {
        postMessage!!.postMessage("Alice", "Hi There!")

        assertMessages(createMessage("Alice", "Hi There!"))
    }

    @Test
    fun userCanPostMultipleMessages() {
        val firstMessage = createMessage("Alice", "Hi There!")
        val secondMessage = createMessage("Alice", "Hi there again!")

        postMessage!!.postMessage("Alice", "Hi There!")
        postMessage!!.postMessage("Alice", "Hi there again!")

        assertMessages(firstMessage, secondMessage)
    }

    @Test
    fun multipleUsersCanPostAMessage() {
        val firstMessage = createMessage("Alice", "Hi There!")
        val secondMessage = createMessage("Bob", "Hi there from Bob!")

        postMessage!!.postMessage("Alice", "Hi There!")
        postMessage!!.postMessage("Bob", "Hi there from Bob!")

        assertMessages(firstMessage, secondMessage)
    }
}