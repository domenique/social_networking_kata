package io.tripled.social.client.application.usecase

import io.tripled.social.client.application.FollowUser
import io.tripled.social.client.domain.SocialNetwork
import io.tripled.social.client.domain.SocialNetworkRepository
import io.tripled.social.client.domain.UserName

class FollowUserUseCase(private val socialNetworkRepository: SocialNetworkRepository) : FollowUser {

    override fun follow(userName: String, userNameToFollow: String): String {
        val socialNetwork: SocialNetwork = socialNetworkRepository.get()

        return socialNetwork.follow(UserName(userName), UserName(userNameToFollow))
    }
}
