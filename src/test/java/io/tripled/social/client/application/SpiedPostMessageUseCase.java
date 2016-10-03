package io.tripled.social.client.application;

public class SpiedPostMessageUseCase implements PostMessageUseCase {

  public String userName;
  public String message;

  @Override
  public String postMessage(String userName, String message) {
    this.userName = userName;
    this.message = message;

    return userName + " posted: " + message;
  }
}
