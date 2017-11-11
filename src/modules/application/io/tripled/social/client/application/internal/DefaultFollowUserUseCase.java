package io.tripled.social.client.application.internal;

import io.tripled.social.client.application.FollowUserUseCase;
import io.tripled.social.client.domain.SocialNetwork;
import io.tripled.social.client.domain.SocialNetworkRepository;
import io.tripled.social.client.domain.UserName;

public class DefaultFollowUserUseCase implements FollowUserUseCase {

  private SocialNetworkRepository socialNetworkRepository;

  public DefaultFollowUserUseCase(SocialNetworkRepository socialNetworkRepository) {
    this.socialNetworkRepository = socialNetworkRepository;
  }

  @Override
  public String follow(String userName, String userNameToFollow) {
    SocialNetwork socialNetwork = socialNetworkRepository.get();

    return socialNetwork.follow(new UserName(userName), new UserName(userNameToFollow));
  }
}
