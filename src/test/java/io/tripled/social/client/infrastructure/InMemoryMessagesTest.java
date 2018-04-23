package io.tripled.social.client.infrastructure;

import io.tripled.social.client.domain.Message;
import io.tripled.social.client.domain.UserName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

class InMemoryMessagesTest {

  private InMemoryMessages messages;
  private TestDateTimeProvider dateTimeProvider;

  @BeforeEach
  void setUp() {
    dateTimeProvider = new TestDateTimeProvider(Clock.fixed(Instant.now(), ZoneId.systemDefault()));
    messages = new InMemoryMessages();
    messages.save(createMessage("Alice", "A message from Alice"));
    messages.save(createMessage("Alice", "A second message from Alice"));
    messages.save(createMessage("Bob", "A message from Bob"));
  }

  @Test
  void returnsAllMessages() {
    List<Message> allMessages = messages.findAll();

    assertThat(allMessages, hasSize(3));
  }

  @Test
  void returnsEmptyList() {
    UserName charlie = new UserName("Charlie");

    List<Message> messagesFromAlice = messages.findAllByUserName(charlie);

    assertThat(messagesFromAlice, hasSize(0));
  }

  @Test
  void canFindMessagesByUserName() {
    UserName alice = new UserName("Alice");

    List<Message> messagesFromAlice = messages.findAllByUserName(alice);

    assertThat(messagesFromAlice, hasSize(2));
    messagesFromAlice.forEach(msg -> assertThat(msg.isWrittenBy(alice), is(true)));
  }

  @Test
  void canFindMessagesByMultipleUsers() {
    UserName alice = new UserName("Alice");
    UserName bob = new UserName("Bob");

    List<Message> messages = this.messages.findAllByUserNames(Arrays.asList(alice, bob));

    assertThat(messages, hasSize(3));
  }

  private Message createMessage(String alice, String message) {
    return new Message(new UserName(alice), message, dateTimeProvider);
  }
}