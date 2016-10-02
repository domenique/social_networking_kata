package io.tripled.social.client.application;

import io.tripled.social.client.domain.SocialNetwork;
import io.tripled.social.client.domain.SocialNetworkRepository;
import io.tripled.social.client.domain.UserName;

public class DefaultReadWallUseCase implements ReadWallUseCase {

  private SocialNetworkRepository socialNetworkRepository;

  public DefaultReadWallUseCase(SocialNetworkRepository socialNetworkRepository) {
    this.socialNetworkRepository = socialNetworkRepository;
  }

  @Override
  public String readWall(String userName) {
    SocialNetwork socialNetwork = socialNetworkRepository.get();

    return socialNetwork.readWall(new UserName(userName));
  }
}
