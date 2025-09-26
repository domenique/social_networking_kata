package io.tripled.social.client.application

import io.tripled.social.client.domain.DateTimeProvider
import io.tripled.social.client.infrastructure.TestSocialNetworkRepository
import org.junit.jupiter.api.Test

class DefaultFollowUserUseCaseTest : AbstractUseCaseTest() {
    private var useCase: DefaultFollowUserUseCase? = null

    override fun initializeUseCase(
        socialNetworkRepository: TestSocialNetworkRepository,
        dateTimeProvider: DateTimeProvider
    ) {
        useCase = DefaultFollowUserUseCase(socialNetworkRepository)
    }

    @Test
    fun canFollow() {
        useCase!!.follow("Alice", "Bob")

        assertUserIsFollowing("Alice", "Bob")
    }

    @Test
    fun canFollowTwoUsers() {
        useCase!!.follow("Alice", "Bob")
        useCase!!.follow("Alice", "Jane")

        assertUserIsFollowing("Alice", "Bob", "Jane")
    }
}