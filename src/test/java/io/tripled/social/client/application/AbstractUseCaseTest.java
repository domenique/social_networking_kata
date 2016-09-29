package io.tripled.social.client.application;

import io.tripled.social.client.domain.DateTimeProvider;
import io.tripled.social.client.domain.Message;
import io.tripled.social.client.domain.MessageRepository;
import io.tripled.social.client.domain.SocialNetwork;
import io.tripled.social.client.domain.UserName;
import io.tripled.social.client.infrastructure.InMemoryMessageRepository;
import io.tripled.social.client.infrastructure.TestDateTimeProvider;
import io.tripled.social.client.infrastructure.TestSocialNetworkRepository;
import org.junit.Before;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

abstract class AbstractUseCaseTest {

  private MessageRepository messageRepository;
  private TestDateTimeProvider dateTimeProvider;

  @Before
  public void setUp() {
    messageRepository = new InMemoryMessageRepository();
    dateTimeProvider = new TestDateTimeProvider(Clock.fixed(Instant.now(), ZoneId.systemDefault()));
    SocialNetwork socialNetwork = new SocialNetwork(messageRepository, dateTimeProvider);
    TestSocialNetworkRepository socialNetworkRepository = new TestSocialNetworkRepository(socialNetwork);

    initializeUseCase(socialNetworkRepository, dateTimeProvider);
  }

  protected abstract void initializeUseCase(TestSocialNetworkRepository socialNetworkRepository, DateTimeProvider dateTimeProvider);


  Message createMessage(String userName, String message) {
    return new Message(new UserName(userName), message, dateTimeProvider);
  }

  void saveMessage(Message... messages) {
    Stream.of(messages)
        .forEach(messageRepository::save);
  }

  void assertMessages(Message... messages) {
    List<Message> storedMessages = messageRepository.findAll();
    assertThat(storedMessages, hasSize(messages.length));
    for (int i = 0; i < messages.length; i++) {
      assertThat(storedMessages.get(i), equalTo(messages[i]));
    }
  }

  void fixateTimeWithOffset(Duration duration) {
    dateTimeProvider.fixateWithOffset(duration);
  }
}
