package io.tripled.social.client;

import io.tripled.social.client.presentation.Input;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

class CucumberInput implements Input {

  private final Deque<String> inputs;

  CucumberInput() {
    inputs = new LinkedList<>();
  }

  CucumberInput(Deque<String> inputs) {
    this.inputs = new LinkedList<>(inputs);
  }

  public void addInput(String... inputs) {
    this.inputs.addAll(Arrays.asList(inputs));
  }

  @Override
  public String read() {
    return inputs.poll();
  }
}
