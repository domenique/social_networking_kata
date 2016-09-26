package io.tripled.social.client.application;

public interface FollowUserUseCase {

  void follow(String userName, String userNameToFollow);
}
