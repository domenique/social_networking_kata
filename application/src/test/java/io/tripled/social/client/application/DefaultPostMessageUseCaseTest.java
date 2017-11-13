package io.tripled.social.client.application;

import io.tripled.social.client.application.internal.DefaultPostMessageUseCase;
import io.tripled.social.client.domain.DateTimeProvider;
import io.tripled.social.client.domain.Message;
import io.tripled.social.client.infrastructure.TestSocialNetworkRepository;
import org.junit.Test;

public class DefaultPostMessageUseCaseTest extends AbstractUseCaseTest {

  private PostMessageUseCase postMessageUseCase;

  @Override
  protected void initializeUseCase(TestSocialNetworkRepository socialNetworkRepository, DateTimeProvider dateTimeProvider) {
    postMessageUseCase = new DefaultPostMessageUseCase(socialNetworkRepository, dateTimeProvider);
  }

  @Test
  public void containsZeroMessages() {
    assertMessages();
  }

  @Test
  public void userCanPostMessage() {
    postMessageUseCase.postMessage("Alice", "Hi There!");

    assertMessages(createMessage("Alice", "Hi There!"));
  }

  @Test
  public void userCanPostMultipleMessages() {
    Message firstMessage = createMessage("Alice", "Hi There!");
    Message secondMessage = createMessage("Alice", "Hi there again!");

    postMessageUseCase.postMessage("Alice", "Hi There!");
    postMessageUseCase.postMessage("Alice", "Hi there again!");

    assertMessages(firstMessage, secondMessage);
  }

  @Test
  public void multipleUsersCanPostAMessage() {
    Message firstMessage = createMessage("Alice", "Hi There!");
    Message secondMessage = createMessage("Bob", "Hi there from Bob!");

    postMessageUseCase.postMessage("Alice", "Hi There!");
    postMessageUseCase.postMessage("Bob", "Hi there from Bob!");

    assertMessages(firstMessage, secondMessage);
  }

}