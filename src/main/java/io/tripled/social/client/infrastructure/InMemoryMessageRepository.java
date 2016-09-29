package io.tripled.social.client.infrastructure;

import io.tripled.social.client.domain.Message;
import io.tripled.social.client.domain.MessageRepository;
import io.tripled.social.client.domain.UserName;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryMessageRepository implements MessageRepository {

  private List<Message> messages = new ArrayList<>();

  @Override
  public List<Message> findAll() {
    return Collections.unmodifiableList(messages);
  }

  @Override
  public void save(Message message) {
    this.messages.add(message);
  }

  @Override
  public List<Message> findAllByUserName(UserName userName) {
    return messages.stream()
        .filter(m -> m.writtenBy(userName))
        .sorted()
        .collect(Collectors.toList());
  }
}
