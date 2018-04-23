package io.tripled.social.client.presentation.controller;

import io.tripled.social.client.application.SpiedReadMessagesUseCase;
import io.tripled.social.client.presentation.TestOutput;
import io.tripled.social.client.presentation.request.ReadCliRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class ReadControllerTest {

  private ReadController readController;
  private SpiedReadMessagesUseCase service;

  @BeforeEach
  void setUp() {
    service = new SpiedReadMessagesUseCase();
    readController = new ReadController(service);
  }

  @Test
  void executesCommand() {
    ReadCliRequest request = new ReadCliRequest("Alice");

    readController.execute(request, new TestOutput());

    assertThat(service.userName, equalTo("Alice"));
  }

}