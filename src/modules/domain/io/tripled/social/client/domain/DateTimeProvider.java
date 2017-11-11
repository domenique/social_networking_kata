package io.tripled.social.client.domain;

import java.time.LocalDateTime;

public interface DateTimeProvider {
  LocalDateTime now();
}
