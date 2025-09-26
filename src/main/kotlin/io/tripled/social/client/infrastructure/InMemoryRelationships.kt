package io.tripled.social.client.infrastructure

import io.tripled.social.client.domain.FollowingRelationship
import io.tripled.social.client.domain.Relationships
import io.tripled.social.client.domain.UserName
import java.util.stream.Collectors

class InMemoryRelationships : Relationships {
    var relationships: MutableSet<FollowingRelationship>

    init {
        this.relationships = HashSet<FollowingRelationship>()
    }

    override fun save(followingRelationship: FollowingRelationship) {
        relationships.add(followingRelationship)
    }

    override fun findRelationshipsFor(userName: UserName): List<FollowingRelationship> {
        return relationships.stream()
            .filter { r: FollowingRelationship? -> r!!.isForUserName(userName) }
            .collect(Collectors.toList())
    }
}
