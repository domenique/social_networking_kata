package io.tripled.social.client.application.usecase

import io.tripled.social.client.application.ReadWall
import io.tripled.social.client.domain.SocialNetwork
import io.tripled.social.client.domain.SocialNetworkRepository
import io.tripled.social.client.domain.UserName

class ReadWallUseCase(private val socialNetworkRepository: SocialNetworkRepository) : ReadWall {

    override fun readWall(userName: String): String {
        val socialNetwork: SocialNetwork = socialNetworkRepository.get()

        return socialNetwork.readWall(UserName(userName))
    }
}
