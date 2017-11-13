package io.tripled.social.client.presentation.console;

import io.tripled.social.client.presentation.Output;

public class ConsoleOutput implements Output {
  @Override
  public void print(String message) {
    System.out.println(message);
  }
}
