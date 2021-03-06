package io.tripled.social.client.application;

import io.tripled.social.client.domain.DateTimeProvider;
import io.tripled.social.client.domain.FollowingRelationship;
import io.tripled.social.client.domain.Message;
import io.tripled.social.client.domain.Messages;
import io.tripled.social.client.domain.Relationships;
import io.tripled.social.client.domain.SocialNetwork;
import io.tripled.social.client.domain.UserName;
import io.tripled.social.client.infrastructure.InMemoryMessages;
import io.tripled.social.client.infrastructure.InMemoryRelationships;
import io.tripled.social.client.infrastructure.TestDateTimeProvider;
import io.tripled.social.client.infrastructure.TestSocialNetworkRepository;
import org.junit.jupiter.api.BeforeEach;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;

abstract class AbstractUseCaseTest {

  private Messages messages;
  private Relationships relationships;
  private TestDateTimeProvider dateTimeProvider;

  @BeforeEach
  public void setUp() {
    messages = new InMemoryMessages();
    relationships = new InMemoryRelationships();
    dateTimeProvider = new TestDateTimeProvider(Clock.fixed(Instant.now(), ZoneId.systemDefault()));
    SocialNetwork socialNetwork = new SocialNetwork(messages, relationships, dateTimeProvider);
    TestSocialNetworkRepository socialNetworkRepository = new TestSocialNetworkRepository(socialNetwork);

    initializeUseCase(socialNetworkRepository, dateTimeProvider);
  }

  protected abstract void initializeUseCase(TestSocialNetworkRepository socialNetworkRepository, DateTimeProvider dateTimeProvider);


  Message createMessage(String userName, String message) {
    return new Message(new UserName(userName), message, dateTimeProvider);
  }

  void saveMessage(Message... messages) {
    Stream.of(messages)
        .forEach(this.messages::save);
  }

  void assertMessages(Message... messages) {
    List<Message> storedMessages = this.messages.findAll();
    assertThat(storedMessages, hasSize(messages.length));
    for (int i = 0; i < messages.length; i++) {
      assertThat(storedMessages.get(i), equalTo(messages[i]));
    }
  }

  void saveFollowingRelationShips(String userName, String... following) {
    UserName un = new UserName(userName);
    Stream.of(following)
        .map(UserName::new)
        .forEach(f -> relationships.save(new FollowingRelationship(un, f)));
  }

  void assertUserIsFollowing(String userName, String... following) {
    Set<UserName> followingUserNames = relationships.findRelationshipsFor(new UserName(userName)).stream()
        .map(FollowingRelationship::getFollowing)
        .collect(Collectors.toSet());

    assertThat(followingUserNames, hasSize(following.length));
    Stream.of(following)
        .map(UserName::new)
        .forEach(u -> assertThat(followingUserNames, hasItem(u)));
  }

  void fixateTimeWithOffset(Duration duration) {
    dateTimeProvider.fixateWithOffset(duration);
  }
}
