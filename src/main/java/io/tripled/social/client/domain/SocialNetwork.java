package io.tripled.social.client.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SocialNetwork {

  private final Messages messages;
  private final Relationships relationships;
  private final DateTimeProvider dateTimeProvider;
  private static final MessageFormatter READING_FRMT = MessageFormatter.ofPattern("%msg (%t)");
  private static final MessageFormatter WALL_FRMT = MessageFormatter.ofPattern("%un - %msg (%t)");

  public SocialNetwork(Messages messages, Relationships relationships, DateTimeProvider dateTimeProvider) {
    this.messages = messages;
    this.relationships = relationships;
    this.dateTimeProvider = dateTimeProvider;
  }

  public void postMessage(Message message) {
    messages.save(message);
  }

  public String read(UserName userName) {
    List<Message> messagesToPrint = messages.findAllByUserName(userName);

    return READING_FRMT.print(messagesToPrint, dateTimeProvider);
  }

  public void follow(UserName userName, UserName userNameToFollow) {
    relationships.save(new FollowingRelationship(userName, userNameToFollow));
  }

  public String readWall(UserName userName) {
    List<UserName> userNames = new ArrayList<>();
    userNames.add(userName);
    userNames.addAll(relationships.findRelationshipsFor(userName).stream()
        .map(FollowingRelationship::getFollowing)
        .collect(Collectors.toList()));

    List<Message> messagesToPrint = messages.findAllByUserNames(userNames);
    return WALL_FRMT.print(messagesToPrint, dateTimeProvider);
  }
}
