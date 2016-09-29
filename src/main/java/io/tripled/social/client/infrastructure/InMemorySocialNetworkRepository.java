package io.tripled.social.client.infrastructure;

import io.tripled.social.client.domain.DateTimeProvider;
import io.tripled.social.client.domain.SocialNetwork;
import io.tripled.social.client.domain.SocialNetworkRepository;

public class InMemorySocialNetworkRepository implements SocialNetworkRepository {

  private final SocialNetwork socialNetwork;

  public InMemorySocialNetworkRepository(DateTimeProvider dateTimeProvider) {
    socialNetwork = new SocialNetwork(new InMemoryMessageRepository(), dateTimeProvider);
  }

  @Override
  public SocialNetwork get() {
    return socialNetwork;
  }
}
