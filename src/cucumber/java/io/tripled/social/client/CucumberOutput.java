package io.tripled.social.client;

import io.tripled.social.client.presentation.Output;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

class CucumberOutput implements Output {

  private final BlockingDeque<String> outputs;
  private CountDownLatch latch = new CountDownLatch(1);

  CucumberOutput() {
    this.outputs = new LinkedBlockingDeque<>();
  }

  @Override
  public void print(String message) {
    String[] split = message.split("\\n");
    for (String part : split) {
      this.outputs.offer(part);
    }
    latch.countDown();
  }

  void assertContains(String[] lines) {
    try {
      latch.await(100, TimeUnit.MILLISECONDS);
      for (String line : lines) {
        String resultLine = outputs.poll();
        assertThat(resultLine, equalTo(line));
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

  }
}
