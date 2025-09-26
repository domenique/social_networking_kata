package io.tripled.social.client.infrastructure

import io.tripled.social.client.domain.FollowingRelationship
import io.tripled.social.client.domain.UserName
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.function.Consumer

internal class InMemoryRelationshipsTest {
    private val relationships: InMemoryRelationships = InMemoryRelationships()

    @BeforeEach
    fun setUp() {
        relationships.save(FollowingRelationship(UserName("Alice"), UserName("Bob")))
        relationships.save(FollowingRelationship(UserName("Alice"), UserName("Charlie")))
        relationships.save(FollowingRelationship(UserName("Charlie"), UserName("Bob")))
    }

    @Test
    fun canFindFollowing() {
        val alice = UserName("Alice")

        val relationshipsOfAlice = relationships.findRelationshipsFor(alice)

        MatcherAssert.assertThat(
            relationshipsOfAlice,
            Matchers.hasSize(2)
        )
        relationshipsOfAlice.forEach(Consumer { r: FollowingRelationship? ->
            MatcherAssert.assertThat(
                r!!.isForUserName(
                    alice
                ), Matchers.`is`(true)
            )
        })
    }

    @Test
    fun returnsEmptyList() {
        val alice = UserName("Bob")

        val relationshipsOfAlice = relationships.findRelationshipsFor(alice)

        MatcherAssert.assertThat(
            relationshipsOfAlice,
            Matchers.hasSize(0)
        )
    }
}