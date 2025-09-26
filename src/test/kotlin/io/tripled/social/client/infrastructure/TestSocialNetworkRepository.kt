package io.tripled.social.client.infrastructure

import io.tripled.social.client.domain.SocialNetwork
import io.tripled.social.client.domain.SocialNetworkRepository

class TestSocialNetworkRepository(private val socialNetwork: SocialNetwork) : SocialNetworkRepository {
    override fun get(): SocialNetwork {
        return socialNetwork
    }
}
