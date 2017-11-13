package io.tripled.social.client.infrastructure;

import io.tripled.social.client.domain.DateTimeProvider;

import java.time.LocalDateTime;

public class SystemDateTimeProvider implements DateTimeProvider {

  @Override
  public LocalDateTime now() {
    return LocalDateTime.now();
  }
}
