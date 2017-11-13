package io.tripled.social.client.presentation.controller;

import io.tripled.social.client.application.SpiedPostMessageUseCase;
import io.tripled.social.client.presentation.TestOutput;
import io.tripled.social.client.presentation.request.PostCliRequest;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class PostControllerTest {

  private PostController postController;
  private SpiedPostMessageUseCase service;

  @Before
  public void setUp() {
    service = new SpiedPostMessageUseCase();
    postController = new PostController(service);
  }

  @Test
  public void executesPostCommand() {
    PostCliRequest request = new PostCliRequest("Alice", "Hi There!");


    postController.execute(request, new TestOutput());

    assertThat(service.userName, equalTo("Alice"));
    assertThat(service.message, equalTo("Hi There!"));
  }

}