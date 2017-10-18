package io.tripled.social.client.presentation.request;

import io.tripled.social.client.presentation.CliRequest;
import io.tripled.social.client.presentation.Output;

public class FollowCliRequest implements CliRequest {
  private final String userName;
  private final String userNameToFollow;

  public FollowCliRequest(String userName, String userNameToFollow) {
    this.userName = userName;
    this.userNameToFollow = userNameToFollow;
  }

  @Override
  public void accept(Visitor visitor, Output output) {
    visitor.visit(this, output);
  }

  public String getUserName() {
    return userName;
  }

  public String getUserNameToFollow() {
    return userNameToFollow;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    FollowCliRequest request = (FollowCliRequest) o;

    if (!userName.equals(request.userName)) return false;
    return userNameToFollow.equals(request.userNameToFollow);
  }

  @Override
  public int hashCode() {
    int result = userName.hashCode();
    result = 31 * result + userNameToFollow.hashCode();
    return result;
  }
}
