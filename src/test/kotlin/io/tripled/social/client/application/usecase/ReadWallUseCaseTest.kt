package io.tripled.social.client.application.usecase

import io.tripled.social.client.domain.DateTimeProvider
import io.tripled.social.client.infrastructure.inmemory.TestSocialNetworkRepository
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test
import java.time.Duration

class ReadWallUseCaseTest : AbstractUseCaseTest() {
    private var useCase: ReadWallUseCase? = null

    override fun initializeUseCase(
        socialNetworkRepository: TestSocialNetworkRepository,
        dateTimeProvider: DateTimeProvider
    ) {
        this.useCase = ReadWallUseCase(socialNetworkRepository)
    }

    @Test
    fun canReadWall() {
        saveMessage(createMessage("Alice", "I love the weather today"))
        saveMessage(createMessage("Bob", "I'm Bob the builder!"))
        fixateTimeWithOffset(Duration.ofMinutes(5))
        saveFollowingRelationShips("Alice", "Bob")

        val message = useCase!!.readWall("Alice")

        MatcherAssert.assertThat<String?>(
            message,
            Matchers.equalTo<String?>("Alice - I love the weather today (5 minutes ago)\nBob - I'm Bob the builder! (5 minutes ago)")
        )
    }
}