package io.tripled.social.client.application;

import io.tripled.social.client.domain.SocialNetwork;
import io.tripled.social.client.domain.SocialNetworkRepository;
import io.tripled.social.client.domain.UserName;

public class DefaultFollowUserUseCase implements FollowUserUseCase {

  private SocialNetworkRepository socialNetworkRepository;

  public DefaultFollowUserUseCase(SocialNetworkRepository socialNetworkRepository) {
    this.socialNetworkRepository = socialNetworkRepository;
  }

  @Override
  public void follow(String userName, String userNameToFollow) {
    SocialNetwork socialNetwork = socialNetworkRepository.get();

    socialNetwork.follow(new UserName(userName), new UserName(userNameToFollow));
  }
}
