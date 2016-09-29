package io.tripled.social.client.domain;

import java.util.List;

public interface MessageRepository {
  List<Message> findAll();

  void save(Message message);

  List<Message> findAllByUserName(UserName userName);
}
