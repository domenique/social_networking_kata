package io.tripled.social.client.infrastructure

import io.tripled.social.client.domain.DateTimeProvider
import io.tripled.social.client.domain.SocialNetwork
import io.tripled.social.client.domain.SocialNetworkRepository

class InMemorySocialNetworkRepository(dateTimeProvider: DateTimeProvider) : SocialNetworkRepository {
    private val socialNetwork: SocialNetwork =
        SocialNetwork(InMemoryMessages(), InMemoryRelationships(), dateTimeProvider)

    override fun get(): SocialNetwork {
        return socialNetwork
    }
}
