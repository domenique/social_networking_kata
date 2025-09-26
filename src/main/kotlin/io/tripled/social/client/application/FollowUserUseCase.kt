package io.tripled.social.client.application

interface FollowUserUseCase {
    fun follow(userName: String, userNameToFollow: String): String
}
