package io.tripled.social.client.domain;

import java.util.List;

public interface Relationships {
  void save(FollowingRelationship followingRelationship);

  List<FollowingRelationship> findRelationshipsFor(UserName userName);
}
