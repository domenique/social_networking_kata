package io.tripled.social.client.presentation.controller;

import io.tripled.social.client.application.SpiedWallMessageApplicationService;
import io.tripled.social.client.presentation.TestOutput;
import io.tripled.social.client.presentation.request.WallCliRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class WallControllerTest {

  private WallController wallController;
  private SpiedWallMessageApplicationService service;

  @BeforeEach
  void setUp() {
    service = new SpiedWallMessageApplicationService();
    wallController = new WallController(service);
  }

  @Test
  void executesCommand() {
    WallCliRequest request = new WallCliRequest("Alice");

    TestOutput output = new TestOutput();
    wallController.execute(request, output);

    assertThat(service.userName, equalTo("Alice"));
    output.assertContains(equalTo("Alice"));
  }

}
