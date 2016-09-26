package io.tripled.social.client.application;

public class DefaultReadMessagesUseCase implements ReadMessagesUseCase {
  @Override
  public String readMessage(String userName) {
    System.out.println(userName + " reads his/her timeline");
    return null;
  }
}
