package io.tripled.social.client.application

import io.tripled.social.client.domain.*
import io.tripled.social.client.infrastructure.InMemoryMessages
import io.tripled.social.client.infrastructure.InMemoryRelationships
import io.tripled.social.client.infrastructure.TestDateTimeProvider
import io.tripled.social.client.infrastructure.TestSocialNetworkRepository
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.jupiter.api.BeforeEach
import java.time.Clock
import java.time.Duration
import java.time.Instant
import java.time.ZoneId
import java.util.stream.Collectors
import java.util.stream.Stream

abstract class AbstractUseCaseTest {
    private val messages: Messages = InMemoryMessages()
    private val relationships: Relationships = InMemoryRelationships()
    private val dateTimeProvider: TestDateTimeProvider = TestDateTimeProvider(Clock.fixed(Instant.now(), ZoneId.systemDefault()))

    @BeforeEach
    fun setUp() {
        val socialNetwork = SocialNetwork(messages, relationships, dateTimeProvider)
        val socialNetworkRepository = TestSocialNetworkRepository(socialNetwork)

        initializeUseCase(socialNetworkRepository, dateTimeProvider)
    }

    protected abstract fun initializeUseCase(
        socialNetworkRepository: TestSocialNetworkRepository,
        dateTimeProvider: DateTimeProvider
    )


    fun createMessage(userName: String, message: String): Message {
        return Message(UserName(userName), message, dateTimeProvider)
    }

    fun saveMessage(vararg messages: Message) {
        Stream.of(*messages)
            .forEach { message: Message? -> this.messages.save(message!!) }
    }

    fun assertMessages(vararg messages: Message) {
        val storedMessages: List<Message?> = this.messages.findAll()
        MatcherAssert.assertThat(storedMessages, Matchers.hasSize<Message?>(messages.size))
        for (i in messages.indices) {
            MatcherAssert.assertThat<Message?>(storedMessages.get(i), Matchers.equalTo<Message?>(messages[i]))
        }
    }

    fun saveFollowingRelationShips(userName: String, vararg following: String?) {
        val un = UserName(userName)
        Stream.of<String?>(*following)
            .map<UserName?> { userName: String? -> UserName(userName!!) }
            .forEach { f: UserName? -> relationships!!.save(FollowingRelationship(un, f!!)) }
    }

    fun assertUserIsFollowing(userName: String, vararg following: String?) {
        val followingUserNames = relationships!!.findRelationshipsFor(UserName(userName)).stream()
            .map<UserName> { obj: FollowingRelationship? -> obj!!.getFollowing() }
            .collect(Collectors.toSet())

        MatcherAssert.assertThat<MutableSet<UserName?>?>(
            followingUserNames,
            Matchers.hasSize<UserName?>(following.size)
        )
        Stream.of<String?>(*following)
            .map<UserName?> { userName: String? -> UserName(userName!!) }
            .forEach { u: UserName? ->
                MatcherAssert.assertThat<MutableSet<UserName?>?>(
                    followingUserNames,
                    Matchers.hasItem<UserName?>(u)
                )
            }
    }

    fun fixateTimeWithOffset(duration: Duration) {
        dateTimeProvider!!.fixateWithOffset(duration)
    }
}
