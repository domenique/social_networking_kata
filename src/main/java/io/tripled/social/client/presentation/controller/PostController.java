package io.tripled.social.client.presentation.controller;

import io.tripled.social.client.application.PostMessageUseCase;
import io.tripled.social.client.presentation.Controller;
import io.tripled.social.client.presentation.Output;
import io.tripled.social.client.presentation.request.PostCliRequest;

public class PostController implements Controller<PostCliRequest> {

  private PostMessageUseCase postMessageUseCase;

  public PostController(PostMessageUseCase postMessageUseCase) {
    this.postMessageUseCase = postMessageUseCase;
  }

  @Override
  public void execute(PostCliRequest request, Output output) {
    output.print(postMessageUseCase.postMessage(request.getUserName(), request.getMessage()));
  }
}
