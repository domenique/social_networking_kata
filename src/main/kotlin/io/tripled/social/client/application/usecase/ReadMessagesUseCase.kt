package io.tripled.social.client.application.usecase

import io.tripled.social.client.application.ReadMessages
import io.tripled.social.client.domain.SocialNetwork
import io.tripled.social.client.domain.SocialNetworkRepository
import io.tripled.social.client.domain.UserName

class ReadMessagesUseCase(private val socialNetworkRepository: SocialNetworkRepository) : ReadMessages {

    override fun readMessage(userName: String): String {
        val socialNetwork: SocialNetwork = socialNetworkRepository.get()

        return socialNetwork.read(UserName(userName))
    }
}
