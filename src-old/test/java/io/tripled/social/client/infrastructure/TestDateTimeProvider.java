package io.tripled.social.client.infrastructure;

import io.tripled.social.client.domain.DateTimeProvider;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class TestDateTimeProvider implements DateTimeProvider{

  private Clock clock;

  public TestDateTimeProvider(Clock clock) {
    this.clock = clock;
  }

  @Override
  public LocalDateTime now() {
    return LocalDateTime.now(clock);
  }

  public void fixate(Instant instant) {
    clock = Clock.fixed(instant, ZoneId.systemDefault());
  }

  public void fixateWithOffset(Duration duration) {
    clock = Clock.offset(clock, duration);
  }
}
