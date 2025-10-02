package io.tripled.social.client.application.usecase

import io.tripled.social.client.application.PostMessage
import io.tripled.social.client.domain.DateTimeProvider
import io.tripled.social.client.domain.Message
import io.tripled.social.client.domain.SocialNetwork
import io.tripled.social.client.domain.SocialNetworkRepository
import io.tripled.social.client.domain.UserName

class PostMessageUseCase(
    private val socialNetworkRepository: SocialNetworkRepository,
    private val dateTimeProvider: DateTimeProvider
) : PostMessage {

    override fun postMessage(userName: String, message: String): String {
        val socialNetwork: SocialNetwork = socialNetworkRepository.get()

        val msg = Message(UserName(userName), message, dateTimeProvider)

        return socialNetwork.postMessage(msg)
    }
}
