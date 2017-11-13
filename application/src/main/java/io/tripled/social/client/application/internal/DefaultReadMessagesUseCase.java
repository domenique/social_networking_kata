package io.tripled.social.client.application.internal;

import io.tripled.social.client.application.ReadMessagesUseCase;
import io.tripled.social.client.domain.SocialNetwork;
import io.tripled.social.client.domain.SocialNetworkRepository;
import io.tripled.social.client.domain.UserName;

public class DefaultReadMessagesUseCase implements ReadMessagesUseCase {

  private SocialNetworkRepository socialNetworkRepository;

  public DefaultReadMessagesUseCase(SocialNetworkRepository socialNetworkRepository) {
    this.socialNetworkRepository = socialNetworkRepository;
  }

  @Override
  public String readMessage(String userName) {
    SocialNetwork socialNetwork = socialNetworkRepository.get();

    return socialNetwork.read(new UserName(userName));
  }
}
