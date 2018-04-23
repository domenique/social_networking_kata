package io.tripled.social.client.presentation;

import org.hamcrest.Matcher;

import java.util.LinkedList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasItems;

public class TestOutput implements Output {

  private final LinkedList<String> outputs;

  public TestOutput() {
    this.outputs = new LinkedList<>();
  }

  @Override
  public void print(String message) {
    this.outputs.add(message);
  }

  public void assertContains(Matcher<String>... message) {
    assertThat(this.outputs, hasItems(message));
  }

  void assertIsEmpty() {
    assertThat(this.outputs, empty());
  }

}
