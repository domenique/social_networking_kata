package io.tripled.social.client.domain;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

public class MessageFormatter {

  private String pattern;

  private MessageFormatter(String pattern) {
    this.pattern = pattern;
  }

  public static MessageFormatter ofPattern(String pattern) {
    return new MessageFormatter(pattern);
  }

  public String print(Message message, DateTimeProvider dateTimeProvider) {
    return pattern
        .replace("%un", message.getUserName().getUserName())
        .replace("%msg", message.getMessage())
        .replace("%t", createTime(message.getTimeStamp(), dateTimeProvider.now()));
  }

  public String print(List<Message> messages, DateTimeProvider dateTimeProvider) {
    StringBuilder builder = new StringBuilder();
    messages
        .forEach(m -> builder.append(print(m, dateTimeProvider)).append(System.lineSeparator()));

    return builder.toString().trim();
  }

  private String createTime(LocalDateTime timeStamp, LocalDateTime now) {
    long qta = diffInSeconds(timeStamp, now);
    String unit = "second";

    if (qta >= 60) {
      unit = "minute";
      qta = qta / 60 % 60;
    }

    if (qta > 1)
      unit += "s";

    return String.format("%d %s ago", qta, unit);
  }

  private long diffInSeconds(LocalDateTime timeStamp, LocalDateTime now) {
    long diffMillis = now.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() - timeStamp.atZone(ZoneId.systemDefault()).toInstant()
        .toEpochMilli();
    return diffMillis / 1000;
  }
}
