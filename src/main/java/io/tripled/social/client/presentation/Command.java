package io.tripled.social.client.presentation;

interface Command {
  boolean supports(String readLine);

  void execute(String inputLine, Output output);
}
