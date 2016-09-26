package io.tripled.social.client.presentation.controller;

import io.tripled.social.client.presentation.Controller;
import io.tripled.social.client.presentation.Output;
import org.hamcrest.Matcher;

import static org.junit.Assert.assertThat;


public class EchoController implements Controller {

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
