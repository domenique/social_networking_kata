package io.tripled.social.client;

import io.tripled.social.client.presentation.Output;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

class CucumberOutput implements Output {

  private final BlockingDeque<String> outputs;

  CucumberOutput() {
    this.outputs = new LinkedBlockingDeque<>();
  }

  @Override
  public void print(String message) {
    String[] split = message.split("\\n");
    for (String part : split) {
      this.outputs.offer(part);
    }
  }

  void assertContains(String... lines) {
    while (outputs.size() < lines.length) {
      try {
        Thread.sleep(5);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    for (String line : lines) {
      String resultLine = outputs.poll();
      assertThat(resultLine, equalTo(line));
    }
  }
}
