package io.tripled.social.client;

import io.tripled.social.client.presentation.Input;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

class CucumberInput implements Input {

  private final BlockingDeque<String> inputs;

  CucumberInput() {
    inputs = new LinkedBlockingDeque<>();
  }

  void sendInput(String input) {
    this.inputs.offer(input);
  }

  @Override
  public String read() {
    return inputs.poll();
  }
}
