package io.tripled.social.client.infrastructure;

import io.tripled.social.client.domain.FollowingRelationship;
import io.tripled.social.client.domain.UserName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

class InMemoryRelationshipsTest {

  private InMemoryRelationships relationships;

  @BeforeEach
  void setUp() {
    relationships = new InMemoryRelationships();
    relationships.save(new FollowingRelationship(new UserName("Alice"), new UserName("Bob")));
    relationships.save(new FollowingRelationship(new UserName("Alice"), new UserName("Charlie")));
    relationships.save(new FollowingRelationship(new UserName("Charlie"), new UserName("Bob")));
  }

  @Test
  void canFindFollowing() {
    UserName alice = new UserName("Alice");

    List<FollowingRelationship> relationshipsOfAlice = relationships.findRelationshipsFor(alice);

    assertThat(relationshipsOfAlice, hasSize(2));
    relationshipsOfAlice.forEach(r -> assertThat(r.isForUserName(alice), is(true)));
  }

  @Test
  void returnsEmptyList() {
    UserName alice = new UserName("Bob");

    List<FollowingRelationship> relationshipsOfAlice = relationships.findRelationshipsFor(alice);

    assertThat(relationshipsOfAlice, hasSize(0));
  }
}