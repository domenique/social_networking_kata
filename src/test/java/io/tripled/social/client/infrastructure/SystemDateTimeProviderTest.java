package io.tripled.social.client.infrastructure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

class SystemDateTimeProviderTest {

  private SystemDateTimeProvider dateTimeProvider;

  @BeforeEach
  void setUp() {
    dateTimeProvider = new SystemDateTimeProvider();
  }

  @Test
  void returnsNow() {
    assertThat(dateTimeProvider.now(), notNullValue());
  }
}