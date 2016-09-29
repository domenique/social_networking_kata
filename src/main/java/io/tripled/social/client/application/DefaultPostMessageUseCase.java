package io.tripled.social.client.application;

import io.tripled.social.client.domain.DateTimeProvider;
import io.tripled.social.client.domain.Message;
import io.tripled.social.client.domain.SocialNetwork;
import io.tripled.social.client.domain.SocialNetworkRepository;
import io.tripled.social.client.domain.UserName;

public class DefaultPostMessageUseCase implements PostMessageUseCase {

  private SocialNetworkRepository socialNetworkRepository;
  private DateTimeProvider dateTimeProvider;

  public DefaultPostMessageUseCase(SocialNetworkRepository socialNetworkRepository, DateTimeProvider dateTimeProvider) {
    this.socialNetworkRepository = socialNetworkRepository;
    this.dateTimeProvider = dateTimeProvider;
  }

  @Override
  public void postMessage(String userName, String message) {
    SocialNetwork socialNetwork = socialNetworkRepository.get();

    Message msg = new Message(new UserName(userName), message, dateTimeProvider);

    socialNetwork.postMessage(msg);
  }
}
