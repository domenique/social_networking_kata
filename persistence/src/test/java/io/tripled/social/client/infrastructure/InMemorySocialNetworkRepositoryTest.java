package io.tripled.social.client.infrastructure;

import io.tripled.social.client.domain.SocialNetwork;
import org.junit.Before;
import org.junit.Test;

import java.time.Clock;

import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;

public class InMemorySocialNetworkRepositoryTest {

  private InMemorySocialNetworkRepository repository;

  @Before
  public void setUp() {
    repository = new InMemorySocialNetworkRepository(new TestDateTimeProvider(Clock.systemDefaultZone()));
  }

  @Test
  public void alwaysReturnsSameInstance() {
    SocialNetwork socialNetwork = repository.get();

    assertThat(socialNetwork, sameInstance(repository.get()));
  }
}