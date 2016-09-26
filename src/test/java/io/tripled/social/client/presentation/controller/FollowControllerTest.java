package io.tripled.social.client.presentation.controller;

import io.tripled.social.client.application.SpiedFollowUserUseCase;
import io.tripled.social.client.presentation.TestOutput;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class FollowControllerTest {

  private FollowController followController;
  private SpiedFollowUserUseCase service;

  @Before
  public void setUp() {
    service = new SpiedFollowUserUseCase();
    followController = new FollowController(service);
  }

  @Test
  public void executesCommand() {
    String message = "Charlie follows Alice";

    followController.execute(message, new TestOutput());

    assertThat(service.userName, equalTo("Charlie"));
    assertThat(service.userNameToFollow, equalTo("Alice"));
  }

  @Test
  public void executesCommandWithTrimmedMessage() {
    String message = "  Charlie    follows   Alice   ";

    followController.execute(message, new TestOutput());

    assertThat(service.userName, equalTo("Charlie"));
    assertThat(service.userNameToFollow, equalTo("Alice"));
  }
}
