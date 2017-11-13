package io.tripled.social.client.application.internal;

import io.tripled.social.client.application.PostMessageUseCase;
import io.tripled.social.client.domain.*;

public class DefaultPostMessageUseCase implements PostMessageUseCase {

  private SocialNetworkRepository socialNetworkRepository;
  private DateTimeProvider dateTimeProvider;

  public DefaultPostMessageUseCase(SocialNetworkRepository socialNetworkRepository, DateTimeProvider dateTimeProvider) {
    this.socialNetworkRepository = socialNetworkRepository;
    this.dateTimeProvider = dateTimeProvider;
  }

  @Override
  public String postMessage(String userName, String message) {
    SocialNetwork socialNetwork = socialNetworkRepository.get();

    Message msg = new Message(new UserName(userName), message, dateTimeProvider);

    return socialNetwork.postMessage(msg);
  }
}
