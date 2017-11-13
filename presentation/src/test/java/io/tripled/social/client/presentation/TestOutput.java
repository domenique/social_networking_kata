package io.tripled.social.client.presentation;

import org.hamcrest.Matcher;

import java.util.LinkedList;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertThat;

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

  public void assertIsEmpty() {
    assertThat(this.outputs.size(), equalTo(0));
  }

  public void assertSize(int size) {
    assertThat(this.outputs.size(), equalTo(size));
  }
}
