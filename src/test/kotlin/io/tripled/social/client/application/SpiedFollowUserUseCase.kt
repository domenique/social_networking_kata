package io.tripled.social.client.application

class SpiedFollowUserUseCase : FollowUserUseCase {
    @JvmField
    var userName: String? = null
    @JvmField
    var userNameToFollow: String? = null

    override fun follow(userName: String, userNameToFollow: String): String {
        this.userName = userName
        this.userNameToFollow = userNameToFollow

        return userName + " will follow " + userNameToFollow
    }
}
