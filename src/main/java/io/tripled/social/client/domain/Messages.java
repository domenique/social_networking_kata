package io.tripled.social.client.domain;

import java.util.List;

public interface Messages {
  List<Message> findAll();

  void save(Message message);

  List<Message> findAllByUserName(UserName userName);

  List<Message> findAllByUserNames(List<UserName> userNames);
}
