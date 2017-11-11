package io.tripled.social.client.infrastructure;

import io.tripled.social.client.domain.FollowingRelationship;
import io.tripled.social.client.domain.Relationships;
import io.tripled.social.client.domain.UserName;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class InMemoryRelationships implements Relationships {

  Set<FollowingRelationship> relationships;

  public InMemoryRelationships() {
    this.relationships = new HashSet<>();
  }

  @Override
  public void save(FollowingRelationship followingRelationship) {
    relationships.add(followingRelationship);
  }

  @Override
  public List<FollowingRelationship> findRelationshipsFor(UserName userName) {
    return relationships.stream()
        .filter(r -> r.isForUserName(userName))
        .collect(Collectors.toList());
  }
}
