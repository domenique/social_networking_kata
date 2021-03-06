package io.tripled.social.client.application;

import io.tripled.social.client.domain.DateTimeProvider;
import io.tripled.social.client.infrastructure.TestSocialNetworkRepository;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class DefaultReadMessagesUseCaseTest extends AbstractUseCaseTest {

  private ReadMessagesUseCase readMessagesUseCase;

  @Override
  protected void initializeUseCase(TestSocialNetworkRepository socialNetworkRepository, DateTimeProvider dateTimeProvider) {
    readMessagesUseCase = new DefaultReadMessagesUseCase(socialNetworkRepository);
  }

  @Test
  void canReadMessage() {
    saveMessage(createMessage("Alice", "I love the weather today"));
    fixateTimeWithOffset(Duration.ofMinutes(5));

    String message = readMessagesUseCase.readMessage("Alice");

    assertThat(message, equalTo("I love the weather today (5 minutes ago)"));
  }


}