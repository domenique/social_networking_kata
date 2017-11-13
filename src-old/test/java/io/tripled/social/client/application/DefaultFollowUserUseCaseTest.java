package io.tripled.social.client.application;

import io.tripled.social.client.domain.DateTimeProvider;
import io.tripled.social.client.infrastructure.TestSocialNetworkRepository;
import org.junit.Test;

public class DefaultFollowUserUseCaseTest extends AbstractUseCaseTest {

  private DefaultFollowUserUseCase useCase;

  @Override
  protected void initializeUseCase(TestSocialNetworkRepository socialNetworkRepository, DateTimeProvider dateTimeProvider) {
    useCase = new DefaultFollowUserUseCase(socialNetworkRepository);
  }

  @Test
  public void canFollow() {
    useCase.follow("Alice", "Bob");

    assertUserIsFollowing("Alice", "Bob");
  }

  @Test
  public void canFollowTwoUsers() {
    useCase.follow("Alice", "Bob");
    useCase.follow("Alice", "Jane");

    assertUserIsFollowing("Alice", "Bob", "Jane");
  }
}