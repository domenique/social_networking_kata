package io.tripled.social.client.infrastructure

import io.cucumber.java.sl.In
import io.tripled.social.client.domain.Message
import io.tripled.social.client.domain.UserName
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.Clock
import java.time.Instant
import java.time.ZoneId
import java.util.*
import java.util.function.Consumer

internal class InMemoryMessagesTest {
    private var messages: InMemoryMessages = InMemoryMessages()
    private var dateTimeProvider: TestDateTimeProvider = TestDateTimeProvider(Clock.fixed(Instant.now(), ZoneId.systemDefault()))

    @BeforeEach
    fun setUp() {
        messages.save(createMessage("Alice", "A message from Alice"))
        messages.save(createMessage("Alice", "A second message from Alice"))
        messages.save(createMessage("Bob", "A message from Bob"))
    }

    @Test
    fun returnsAllMessages() {
        val allMessages = messages.findAll()

        MatcherAssert.assertThat(allMessages, Matchers.hasSize(3))
    }

    @Test
    fun returnsEmptyList() {
        val charlie = UserName("Charlie")

        val messagesFromAlice = messages.findAllByUserName(charlie)

        MatcherAssert.assertThat<List<Message?>?>(messagesFromAlice, Matchers.hasSize<Message?>(0))
    }

    @Test
    fun canFindMessagesByUserName() {
        val alice = UserName("Alice")

        val messagesFromAlice = messages.findAllByUserName(alice)

        MatcherAssert.assertThat(messagesFromAlice, Matchers.hasSize(2))
        messagesFromAlice.forEach(Consumer { msg: Message? ->
            MatcherAssert.assertThat(
                msg!!.isWrittenBy(alice),
                Matchers.`is`(true)
            )
        })
    }

    @Test
    fun canFindMessagesByMultipleUsers() {
        val alice = UserName("Alice")
        val bob = UserName("Bob")

        val messages = this.messages.findAllByUserNames(Arrays.asList<UserName>(alice, bob))

        MatcherAssert.assertThat(messages, Matchers.hasSize(3))
    }

    private fun createMessage(alice: String, message: String): Message {
        return Message(UserName(alice), message, dateTimeProvider!!)
    }
}