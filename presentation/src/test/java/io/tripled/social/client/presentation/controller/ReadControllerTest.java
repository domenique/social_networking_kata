package io.tripled.social.client.presentation.controller;

import io.tripled.social.client.test.SpiedReadMessagesUseCase;
import io.tripled.social.client.presentation.TestOutput;
import io.tripled.social.client.presentation.request.ReadCliRequest;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
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
    ReadCliRequest request = new ReadCliRequest("Alice");

    readController.execute(request, new TestOutput());

    assertThat(service.userName, equalTo("Alice"));
  }

}