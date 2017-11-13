package io.tripled.social.client.test;

import io.tripled.social.client.application.FollowUserUseCase;

public class SpiedFollowUserUseCase implements FollowUserUseCase {

  public String userName;
  public String userNameToFollow;

  @Override
  public String follow(String userName, String userNameToFollow) {
    this.userName = userName;
    this.userNameToFollow = userNameToFollow;

    return userName + " will follow " + userNameToFollow;
  }
}
