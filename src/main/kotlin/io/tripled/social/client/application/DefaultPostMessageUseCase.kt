package io.tripled.social.client.application

import io.tripled.social.client.domain.DateTimeProvider
import io.tripled.social.client.domain.SocialNetwork
import io.tripled.social.client.domain.SocialNetworkRepository
import io.tripled.social.client.domain.UserName

class DefaultPostMessageUseCase(socialNetworkRepository: SocialNetworkRepository, dateTimeProvider: DateTimeProvider) :
    PostMessageUseCase {
    private val socialNetworkRepository: SocialNetworkRepository
    private val dateTimeProvider: DateTimeProvider

    init {
        this.socialNetworkRepository = socialNetworkRepository
        this.dateTimeProvider = dateTimeProvider
    }

    override fun postMessage(userName: kotlin.String, message: kotlin.String): kotlin.String {
        val socialNetwork: SocialNetwork = socialNetworkRepository.get()

        val msg = io.tripled.social.client.domain.Message(UserName(userName), message, dateTimeProvider)

        return socialNetwork.postMessage(msg)
    }
}
