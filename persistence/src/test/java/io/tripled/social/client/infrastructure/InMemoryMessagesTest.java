package io.tripled.social.client.infrastructure;

import io.tripled.social.client.domain.Message;
import io.tripled.social.client.domain.UserName;
import org.junit.Before;
import org.junit.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class InMemoryMessagesTest {

  private InMemoryMessages messages;
  private TestDateTimeProvider dateTimeProvider;

  @Before
  public void setUp() {
    dateTimeProvider = new TestDateTimeProvider(Clock.fixed(Instant.now(), ZoneId.systemDefault()));
    messages = new InMemoryMessages();
    messages.save(createMessage("Alice", "A message from Alice"));
    messages.save(createMessage("Alice", "A second message from Alice"));
    messages.save(createMessage("Bob", "A message from Bob"));
  }

  @Test
  public void returnsAllMessages() {
    List<Message> allMessages = messages.findAll();

    assertThat(allMessages.size(), equalTo(3));
  }

  @Test
  public void returnsEmptyList() {
    UserName charlie = new UserName("Charlie");

    List<Message> messagesFromAlice = messages.findAllByUserName(charlie);

    assertThat(messagesFromAlice.size(), equalTo(0));
  }

  @Test
  public void canFindMessagesByUserName() {
    UserName alice = new UserName("Alice");

    List<Message> messagesFromAlice = messages.findAllByUserName(alice);

    assertThat(messagesFromAlice.size(), equalTo(2));
    messagesFromAlice.forEach(msg -> assertThat(msg.isWrittenBy(alice), is(true)));
  }

  @Test
  public void canFindMessagesByMultipleUsers() {
    UserName alice = new UserName("Alice");
    UserName bob = new UserName("Bob");

    List<Message> messages = this.messages.findAllByUserNames(Arrays.asList(alice, bob));

    assertThat(messages.size(), equalTo(3));
  }

  private Message createMessage(String alice, String message) {
    return new Message(new UserName(alice), message, dateTimeProvider);
  }
}