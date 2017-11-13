package io.tripled.social.client.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SocialNetwork {

  private static final MessagePrinter READING_PRINTER = MessagePrinter.ofPattern("%msg (%t)");
  private static final MessagePrinter WALL_PRINTER = MessagePrinter.ofPattern("%un - %msg (%t)");
  private final Messages messages;
  private final Relationships relationships;
  private final DateTimeProvider dateTimeProvider;

  public SocialNetwork(Messages messages, Relationships relationships, DateTimeProvider dateTimeProvider) {
    this.messages = messages;
    this.relationships = relationships;
    this.dateTimeProvider = dateTimeProvider;
  }

  public String postMessage(Message message) {
    messages.save(message);

    return message.getUserName() + " posted: " + message.getMessage();
  }

  public String read(UserName userName) {
    List<Message> messagesToPrint = messages.findAllByUserName(userName);

    return printAll(messagesToPrint, READING_PRINTER);
  }

  public String follow(UserName userName, UserName userNameToFollow) {
    relationships.save(new FollowingRelationship(userName, userNameToFollow));

    return userName + " will follow " + userNameToFollow;
  }

  public String readWall(UserName userName) {
    List<UserName> userNames = new ArrayList<>();
    userNames.add(userName);
    userNames.addAll(relationships.findRelationshipsFor(userName).stream()
        .map(FollowingRelationship::getFollowing)
        .collect(Collectors.toList()));

    List<Message> messagesToPrint = messages.findAllByUserNames(userNames);

    return printAll(messagesToPrint, WALL_PRINTER);
  }

  private String printAll(List<Message> messagesToPrint, MessagePrinter printer) {
    return messagesToPrint.stream()
        .map(msg -> msg.printWith(printer, dateTimeProvider))
        .collect(Collectors.joining(System.lineSeparator()))
        .trim();
  }
}
