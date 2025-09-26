package io.tripled.social.client.domain

data class FollowingRelationship(
    private val userName: UserName,
    private val following: UserName
) {
    fun isForUserName(userName: UserName): Boolean = this.userName == userName

    fun getFollowing(): UserName = following
}