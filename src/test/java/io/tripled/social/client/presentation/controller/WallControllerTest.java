package io.tripled.social.client.presentation.controller;

import io.tripled.social.client.application.SpiedWallMessageApplicationService;
import io.tripled.social.client.presentation.TestOutput;
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
    String message = "wall Alice";

    wallController.execute(message, new TestOutput());

    assertThat(service.userName, equalTo("Alice"));
  }

  @Test
  public void executesCommandWithTrimmedMessage() {
    String message = "wall  Alice  ";

    wallController.execute(message, new TestOutput());

    assertThat(service.userName, equalTo("Alice"));
  }
}
