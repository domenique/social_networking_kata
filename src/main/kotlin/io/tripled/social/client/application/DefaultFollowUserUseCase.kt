package io.tripled.social.client.application

import io.tripled.social.client.domain.SocialNetwork
import io.tripled.social.client.domain.SocialNetworkRepository
import io.tripled.social.client.domain.UserName

class DefaultFollowUserUseCase(socialNetworkRepository: SocialNetworkRepository) : FollowUserUseCase {
    private val socialNetworkRepository: SocialNetworkRepository

    init {
        this.socialNetworkRepository = socialNetworkRepository
    }

    override fun follow(userName: String, userNameToFollow: String): String {
        val socialNetwork: SocialNetwork = socialNetworkRepository.get()

        return socialNetwork.follow(UserName(userName), UserName(userNameToFollow))
    }
}
