package io.tripled.social.client.presentation.controller;

import io.tripled.social.client.application.SpiedReadMessagesUseCase;
import io.tripled.social.client.presentation.TestOutput;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class ReadControllerTest {

  private ReadController readController;
  private SpiedReadMessagesUseCase service;

  @Before
  public void setUp() {
    service = new SpiedReadMessagesUseCase();
    readController = new ReadController(service);
  }

  @Test
  public void executesCommand() {
    String message = "Alice";

    readController.execute(message, new TestOutput());

    assertThat(service.userName, equalTo("Alice"));
  }

  @Test
  public void executesCommandWithTrimmedMessage() {
    String message = "  Alice  ";

    readController.execute(message, new TestOutput());

    assertThat(service.userName, equalTo("Alice"));
  }
}