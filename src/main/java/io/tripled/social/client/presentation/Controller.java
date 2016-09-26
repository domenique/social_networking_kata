package io.tripled.social.client.presentation;

public interface Controller {
  boolean supports(String readLine);

  void execute(String inputLine, Output output);
}
