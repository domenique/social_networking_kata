package io.tripled.social.client.domain

interface SocialNetworkRepository {
    fun get(): SocialNetwork
}
