package io.tripled.social.client.domain;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class MessagePrinter {

  private String pattern;

  private MessagePrinter(String pattern) {
    this.pattern = pattern;
  }

  static MessagePrinter ofPattern(String pattern) {
    return new MessagePrinter(pattern);
  }

  public String print(DateTimeProvider dateTimeProvider, String userName, String messageBody, LocalDateTime timeStamp) {
    return pattern
        .replace("%un", userName)
        .replace("%msg", messageBody)
        .replace("%t", createTime(timeStamp, dateTimeProvider.now()));
  }

  private String createTime(LocalDateTime timeStamp, LocalDateTime now) {
    long qta = diffInSeconds(timeStamp, now);
    String unit = "second";

    if (qta >= 60) {
      unit = "minute";
      qta = qta / 60 % 60;
    }

    if (qta > 1) {
      unit += "s";
    }

    return String.format("%d %s ago", qta, unit);
  }

  private long diffInSeconds(LocalDateTime timeStamp, LocalDateTime now) {
    long diffMillis = now.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() -
        timeStamp.atZone(ZoneId.systemDefault()).toInstant()
            .toEpochMilli();
    return diffMillis / 1000;
  }
}
