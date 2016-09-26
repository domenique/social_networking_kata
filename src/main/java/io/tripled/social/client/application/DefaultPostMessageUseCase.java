package io.tripled.social.client.application;

public class DefaultPostMessageUseCase implements PostMessageUseCase {

  @Override
  public void postMessage(String userName, String message) {
    System.out.println(userName + " Posted message: " + message);
  }
}
