package io.tripled.social.client.application;

import io.tripled.social.client.domain.DateTimeProvider;
import io.tripled.social.client.infrastructure.TestSocialNetworkRepository;
import org.junit.Test;

import java.time.Duration;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class DefaultReadWallUseCaseTest extends AbstractUseCaseTest {

  private DefaultReadWallUseCase useCase;

  @Override
  protected void initializeUseCase(TestSocialNetworkRepository socialNetworkRepository, DateTimeProvider dateTimeProvider) {
    this.useCase = new DefaultReadWallUseCase(socialNetworkRepository);
  }

  @Test
  public void canReadWall() {
    saveMessage(createMessage("Alice", "I love the weather today"));
    saveMessage(createMessage("Bob", "I'm Bob the builder!"));
    fixateTimeWithOffset(Duration.ofMinutes(5));
    saveFollowingRelationShips("Alice", "Bob");

    String message = useCase.readWall("Alice");

    assertThat(message, equalTo("Alice - I love the weather today (5 minutes ago)\nBob - I'm Bob the builder! (5 minutes ago)"));
  }
}