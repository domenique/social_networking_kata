package io.tripled.social.client.presentation.controller;

import io.tripled.social.client.application.SpiedFollowUserUseCase;
import io.tripled.social.client.presentation.TestOutput;
import io.tripled.social.client.presentation.request.FollowCliRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class FollowControllerTest {

  private FollowController followController;
  private SpiedFollowUserUseCase service;

  @BeforeEach
  void setUp() {
    service = new SpiedFollowUserUseCase();
    followController = new FollowController(service);
  }

  @Test
  void executesCommand() {
    FollowCliRequest request = new FollowCliRequest("Charlie", "Alice");

    followController.execute(request, new TestOutput());

    assertThat(service.userName, equalTo("Charlie"));
    assertThat(service.userNameToFollow, equalTo("Alice"));
  }

}
