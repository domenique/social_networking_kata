package io.tripled.social.client.application;

import io.tripled.social.client.application.internal.DefaultFollowUserUseCase;
import io.tripled.social.client.application.internal.DefaultPostMessageUseCase;
import io.tripled.social.client.application.internal.DefaultReadMessagesUseCase;
import io.tripled.social.client.application.internal.DefaultReadWallUseCase;
import io.tripled.social.client.domain.DateTimeProvider;
import io.tripled.social.client.domain.SocialNetworkRepository;

public class UseCaseFactory {
  private final SocialNetworkRepository socialNetworkRepository;

  public UseCaseFactory(SocialNetworkRepository socialNetworkRepository) {
    this.socialNetworkRepository = socialNetworkRepository;
  }

  public PostMessageUseCase createPostMessageUseCase(DateTimeProvider dateTimeProvider) {
    return new DefaultPostMessageUseCase(socialNetworkRepository, dateTimeProvider);
  }

  public ReadMessagesUseCase createReadMessagesUseCase() {
    return new DefaultReadMessagesUseCase(socialNetworkRepository);
  }

  public ReadWallUseCase createReadWallUseCase() {
    return new DefaultReadWallUseCase(socialNetworkRepository);
  }

  public FollowUserUseCase createFollowUserUseCase() {
    return new DefaultFollowUserUseCase(socialNetworkRepository);
  }
}
