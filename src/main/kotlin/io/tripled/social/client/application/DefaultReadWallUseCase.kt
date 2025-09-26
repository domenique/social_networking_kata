package io.tripled.social.client.application

import io.tripled.social.client.domain.SocialNetwork
import io.tripled.social.client.domain.SocialNetworkRepository
import io.tripled.social.client.domain.UserName

class DefaultReadWallUseCase(socialNetworkRepository: SocialNetworkRepository) : ReadWallUseCase {
    private val socialNetworkRepository: SocialNetworkRepository

    init {
        this.socialNetworkRepository = socialNetworkRepository
    }

    override fun readWall(userName: String): String {
        val socialNetwork: SocialNetwork = socialNetworkRepository.get()

        return socialNetwork.readWall(UserName(userName))
    }
}
