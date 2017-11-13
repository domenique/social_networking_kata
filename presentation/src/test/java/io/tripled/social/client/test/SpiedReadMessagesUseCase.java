package io.tripled.social.client.test;

import io.tripled.social.client.application.ReadMessagesUseCase;

public class SpiedReadMessagesUseCase implements ReadMessagesUseCase {

  public String userName;

  @Override
  public String readMessage(String userName) {
    this.userName = userName;
    return null;
  }
}
