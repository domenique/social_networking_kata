package io.tripled.social.client.domain;

import java.util.Objects;

public class FollowingRelationship {

  private final UserName userName;
  private final UserName following;

  public FollowingRelationship(UserName userName, UserName following) {
    this.userName = userName;
    this.following = following;
  }

  public boolean isForUserName(UserName userName) {
    return this.userName.equals(userName);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    FollowingRelationship that = (FollowingRelationship) o;
    return Objects.equals(userName, that.userName) &&
        Objects.equals(following, that.following);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userName, following);
  }

  public UserName getFollowing() {
    return following;
  }
}
