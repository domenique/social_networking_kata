package io.tripled.social.client.application

interface FollowUser {
    fun follow(userName: String, userNameToFollow: String): String
}
