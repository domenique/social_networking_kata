package io.tripled.social.client.domain;

import java.util.List;

public class SocialNetwork {

  private final MessageRepository messageRepository;
  private final DateTimeProvider dateTimeProvider;
  private static final MessageFormatter READING_FRMT = MessageFormatter.ofPattern("%msg (%t)");

  public SocialNetwork(MessageRepository messageRepository, DateTimeProvider dateTimeProvider) {
    this.messageRepository = messageRepository;
    this.dateTimeProvider = dateTimeProvider;
  }

  public void postMessage(Message message) {
    messageRepository.save(message);
  }

  public String read(UserName userName) {
    List<Message> messages = messageRepository.findAllByUserName(userName);

    return READING_FRMT.print(messages, dateTimeProvider);
  }
}
