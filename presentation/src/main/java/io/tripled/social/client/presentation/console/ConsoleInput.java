package io.tripled.social.client.presentation.console;

import io.tripled.social.client.presentation.Input;

import java.io.BufferedReader;
import java.io.IOException;

public class ConsoleInput implements Input {

  private final BufferedReader bufferedReader;

  public ConsoleInput(BufferedReader bufferedReader) {
    this.bufferedReader = bufferedReader;
  }

  @Override
  public String read() {
    try {
      return bufferedReader.readLine();
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }
}
