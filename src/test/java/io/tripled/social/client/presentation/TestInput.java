package io.tripled.social.client.presentation;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

class TestInput implements Input {

  private final Deque<String> inputs;

  TestInput() {
    inputs = new LinkedList<>();
  }

  void addInput(String... inputs) {
    this.inputs.addAll(Arrays.asList(inputs));
  }

  @Override
  public String read() {
    return inputs.poll();
  }
}
