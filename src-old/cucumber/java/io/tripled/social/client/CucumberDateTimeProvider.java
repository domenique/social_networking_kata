package io.tripled.social.client;

import io.tripled.social.client.domain.DateTimeProvider;

public class CucumberDateTimeProvider implements DateTimeProvider {

  private Clock clock;

  public CucumberDateTimeProvider(Clock clock) {
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
