package io.tripled.social.client.domain

interface Relationships {
    fun save(followingRelationship: FollowingRelationship)
    fun findRelationshipsFor(userName: UserName): List<FollowingRelationship>
}