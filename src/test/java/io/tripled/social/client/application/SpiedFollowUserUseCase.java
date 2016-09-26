package io.tripled.social.client.application;

public class SpiedFollowUserUseCase implements FollowUserUseCase {

  public String userName;
  public String userNameToFollow;

  @Override
  public void follow(String userName, String userNameToFollow) {
    this.userName = userName;
    this.userNameToFollow = userNameToFollow;
  }
}
