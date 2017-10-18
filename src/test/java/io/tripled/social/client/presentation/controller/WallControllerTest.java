package io.tripled.social.client.presentation.controller;

import io.tripled.social.client.application.SpiedWallMessageApplicationService;
import io.tripled.social.client.presentation.TestOutput;
import io.tripled.social.client.presentation.request.WallCliRequest;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class WallControllerTest {

  private WallController wallController;
  private SpiedWallMessageApplicationService service;

  @Before
  public void setUp() {
    service = new SpiedWallMessageApplicationService();
    wallController = new WallController(service);
  }

  @Test
  public void executesCommand() {
    WallCliRequest request = new WallCliRequest("Alice");

    TestOutput output = new TestOutput();
    wallController.execute(request, output);

    assertThat(service.userName, equalTo("Alice"));
    output.assertContains(equalTo("Alice"));
  }

}
