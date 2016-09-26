package io.tripled.social.client.application;

public class DefaultFollowUserUseCase implements FollowUserUseCase {
  @Override
  public void follow(String userName, String userNameToFollow) {
    System.out.println(userName + " will follow " + userNameToFollow);
  }
}
