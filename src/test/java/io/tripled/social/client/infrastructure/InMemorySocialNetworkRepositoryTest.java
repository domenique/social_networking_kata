package io.tripled.social.client.infrastructure;

import io.tripled.social.client.domain.SocialNetwork;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Clock;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.sameInstance;

class InMemorySocialNetworkRepositoryTest {

  private InMemorySocialNetworkRepository repository;

  @BeforeEach
  void setUp() {
    repository = new InMemorySocialNetworkRepository(new TestDateTimeProvider(Clock.systemDefaultZone()));
  }

  @Test
  void alwaysReturnsSameInstance() {
    SocialNetwork socialNetwork = repository.get();

    assertThat(socialNetwork, sameInstance(repository.get()));
  }
}