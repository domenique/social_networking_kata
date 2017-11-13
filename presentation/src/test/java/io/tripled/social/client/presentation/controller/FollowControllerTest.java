package io.tripled.social.client.presentation.controller;

import io.tripled.social.client.test.SpiedFollowUserUseCase;
import io.tripled.social.client.presentation.TestOutput;
import io.tripled.social.client.presentation.request.FollowCliRequest;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
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
    FollowCliRequest request = new FollowCliRequest("Charlie", "Alice");

    followController.execute(request, new TestOutput());

    assertThat(service.userName, equalTo("Charlie"));
    assertThat(service.userNameToFollow, equalTo("Alice"));
  }

}
