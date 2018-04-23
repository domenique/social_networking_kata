package io.tripled.social.client.presentation.controller;

import io.tripled.social.client.application.SpiedPostMessageUseCase;
import io.tripled.social.client.presentation.TestOutput;
import io.tripled.social.client.presentation.request.PostCliRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class PostControllerTest {

  private PostController postController;
  private SpiedPostMessageUseCase service;

  @BeforeEach
  void setUp() {
    service = new SpiedPostMessageUseCase();
    postController = new PostController(service);
  }

  @Test
  void executesPostCommand() {
    PostCliRequest request = new PostCliRequest("Alice", "Hi There!");


    postController.execute(request, new TestOutput());

    assertThat(service.userName, equalTo("Alice"));
    assertThat(service.message, equalTo("Hi There!"));
  }

}