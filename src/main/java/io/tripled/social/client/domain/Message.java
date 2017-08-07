package io.tripled.social.client.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class Message implements Comparable<Message> {

  private UserName userName;
  private String message;
  private LocalDateTime localDateTime;

  public Message(UserName userName, String message, DateTimeProvider dateTimeProvider) {
    this.userName = userName;
    this.message = message;
    this.localDateTime = dateTimeProvider.now();
  }

  UserName getUserName() {
    return userName;
  }

  String getMessage() {
    return message;
  }

  LocalDateTime getTimeStamp() {
    return localDateTime;
  }

  String printWith(MessagePrinter printer, DateTimeProvider dateTimeProvider) {
    return printer.print(dateTimeProvider, userName.getUserName(), message, localDateTime);
}

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Message message1 = (Message) o;
    return Objects.equals(userName, message1.userName) &&
           Objects.equals(message, message1.message) &&
           Objects.equals(localDateTime, message1.localDateTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userName, message, localDateTime);
  }

  public boolean isWrittenBy(UserName userName) {
    return this.userName.equals(userName);
  }

  @Override
  public int compareTo(Message other) {
    return other.localDateTime.compareTo(localDateTime);
  }
}
