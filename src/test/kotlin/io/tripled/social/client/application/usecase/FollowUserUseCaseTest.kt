package io.tripled.social.client.application.usecase

import io.tripled.social.client.domain.DateTimeProvider
import io.tripled.social.client.infrastructure.inmemory.TestSocialNetworkRepository
import org.junit.jupiter.api.Test

class FollowUserUseCaseTest : AbstractUseCaseTest() {
    private var useCase: FollowUserUseCase? = null

    override fun initializeUseCase(
        socialNetworkRepository: TestSocialNetworkRepository,
        dateTimeProvider: DateTimeProvider
    ) {
        useCase = FollowUserUseCase(socialNetworkRepository)
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