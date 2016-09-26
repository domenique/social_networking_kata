package io.tripled.social.client.presentation.controller;

import io.tripled.social.client.application.SpiedPostMessageUseCase;
import io.tripled.social.client.presentation.TestOutput;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class PostControllerTest {

  private PostController postController;
  private SpiedPostMessageUseCase service;

  @Before
  public void setUp() {
    service = new SpiedPostMessageUseCase();
    postController = new PostController(service);
  }

  @Test
  public void handlesNullGracefully() {
    boolean supports = postController.supports(null);

    assertThat(supports, is(false));
  }

  @Test
  public void handlesEmptyString() {
    boolean supports = postController.supports("");

    assertThat(supports, is(false));
  }

  @Test
  public void handlesNonPostMessage() {
    boolean supports = postController.supports("Alice wall");

    assertThat(supports, is(false));
  }

  @Test
  public void supportsPostMessage() {
    String message = "Alice -> Hi there!";

    boolean supports = postController.supports(message);

    assertThat(supports, is(true));
  }

  @Test
  public void executesPostCommand() {
    String message = "Alice -> Hi There!";

    postController.execute(message, new TestOutput());

    assertThat(service.userName, equalTo("Alice"));
    assertThat(service.message, equalTo("Hi There!"));
  }

  @Test
  public void executesPostCommandWithTrimmedNameAndMessage() {
    String message = "  Alice   ->   Hi There!  ";

    postController.execute(message, new TestOutput());

    assertThat(service.userName, equalTo("Alice"));
    assertThat(service.message, equalTo("Hi There!"));
  }
}