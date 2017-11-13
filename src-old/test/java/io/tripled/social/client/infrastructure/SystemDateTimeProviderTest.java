package io.tripled.social.client.infrastructure;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class SystemDateTimeProviderTest {

  private SystemDateTimeProvider dateTimeProvider;

  @Before
  public void setUp() {
    dateTimeProvider = new SystemDateTimeProvider();
  }

  @Test
  public void returnsNow() {
    assertThat(dateTimeProvider.now(), notNullValue());
  }
}