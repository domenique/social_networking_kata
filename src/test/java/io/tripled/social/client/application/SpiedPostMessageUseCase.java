package io.tripled.social.client.application;

public class SpiedPostMessageUseCase implements PostMessageUseCase {

  public String userName;
  public String message;

  @Override
  public void postMessage(String userName, String message) {
    this.userName = userName;
    this.message = message;
  }
}
