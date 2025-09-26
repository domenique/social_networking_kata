package io.tripled.social.client.application

import io.tripled.social.client.domain.SocialNetwork
import io.tripled.social.client.domain.SocialNetworkRepository
import io.tripled.social.client.domain.UserName

class DefaultReadMessagesUseCase(socialNetworkRepository: SocialNetworkRepository) : ReadMessagesUseCase {
    private val socialNetworkRepository: SocialNetworkRepository

    init {
        this.socialNetworkRepository = socialNetworkRepository
    }

    override fun readMessage(userName: String): String {
        val socialNetwork: SocialNetwork = socialNetworkRepository.get()

        return socialNetwork.read(UserName(userName))
    }
}
