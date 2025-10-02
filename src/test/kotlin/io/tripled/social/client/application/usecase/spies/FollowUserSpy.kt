package io.tripled.social.client.application.usecase.spies

import io.tripled.social.client.application.FollowUser

class FollowUserSpy(
    var userName: String? = null,
    var userNameToFollow: String? = null
) : FollowUser {

    override fun follow(userName: String, userNameToFollow: String): String {
        this.userName = userName
        this.userNameToFollow = userNameToFollow

        return "$userName will follow $userNameToFollow"
    }
}
