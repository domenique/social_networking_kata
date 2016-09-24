package io.tripled.social.client;

import io.tripled.social.client.presentation.Output;
import org.hamcrest.Matcher;

import java.util.LinkedList;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

class CucumberOutput implements Output {

  private final LinkedList<String> outputs;

  CucumberOutput() {
    this.outputs = new LinkedList<>();
  }

  @Override
  public void print(String message) {
    this.outputs.add(message);
  }

  void assertContains(Matcher<String> message) {
    assertThat("The SocialNetwork did not response the expected message.", this.outputs, hasItem(message));
  }

  void assertSize(int size) {
    assertThat("The SocialNetwork did not respond the expected amount of messages.", this.outputs, hasSize(size));
  }
}
