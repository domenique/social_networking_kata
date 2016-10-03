package io.tripled.social.client.application;

public interface FollowUserUseCase {

  String follow(String userName, String userNameToFollow);
}
