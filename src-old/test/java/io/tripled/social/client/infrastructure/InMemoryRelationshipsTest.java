package io.tripled.social.client.infrastructure;

import io.tripled.social.client.domain.FollowingRelationship;
import io.tripled.social.client.domain.UserName;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class InMemoryRelationshipsTest {

  private InMemoryRelationships relationships;

  @Before
  public void setUp() {
    relationships = new InMemoryRelationships();
    relationships.save(new FollowingRelationship(new UserName("Alice"), new UserName("Bob")));
    relationships.save(new FollowingRelationship(new UserName("Alice"), new UserName("Charlie")));
    relationships.save(new FollowingRelationship(new UserName("Charlie"), new UserName("Bob")));
  }

  @Test
  public void canFindFollowing() {
    UserName alice = new UserName("Alice");

    List<FollowingRelationship> relationshipsOfAlice = relationships.findRelationshipsFor(alice);

    assertThat(relationshipsOfAlice, hasSize(2));
    relationshipsOfAlice.forEach(r -> assertThat(r.isForUserName(alice), is(true)));
  }

  @Test
  public void returnsEmptyList() {
    UserName alice = new UserName("Bob");

    List<FollowingRelationship> relationshipsOfAlice = relationships.findRelationshipsFor(alice);

    assertThat(relationshipsOfAlice, hasSize(0));
  }
}