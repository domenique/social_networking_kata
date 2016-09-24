package io.tripled.social.client.presentation;

import org.hamcrest.Matcher;

import static org.junit.Assert.assertThat;


public class EchoCommand implements Command {

  private String message;

  @Override
  public boolean supports(String readLine) {
    return true;
  }

  @Override
  public void execute(String inputLine, Output output) {
    message = inputLine;
    output.print(inputLine);
  }

  public void assertMessage(Matcher<String> matcher) {
    assertThat(message, matcher);
  }
}
