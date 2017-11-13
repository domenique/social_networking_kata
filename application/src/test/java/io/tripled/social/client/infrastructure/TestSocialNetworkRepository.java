package io.tripled.social.client.infrastructure;

import io.tripled.social.client.domain.SocialNetwork;
import io.tripled.social.client.domain.SocialNetworkRepository;

public class TestSocialNetworkRepository implements SocialNetworkRepository {

  private SocialNetwork socialNetwork;

  public TestSocialNetworkRepository(SocialNetwork socialNetwork) {
    this.socialNetwork = socialNetwork;
  }

  @Override
  public SocialNetwork get() {
    return socialNetwork;
  }
}
