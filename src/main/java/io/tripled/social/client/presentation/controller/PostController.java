package io.tripled.social.client.presentation.controller;

import io.tripled.social.client.application.PostMessageUseCase;
import io.tripled.social.client.presentation.Output;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PostController extends AbstractController {

  private PostMessageUseCase postMessageUseCase;

  public PostController(PostMessageUseCase postMessageUseCase) {
    this.postMessageUseCase = postMessageUseCase;
  }

  @Override
  protected Pattern getPattern() {
    return Pattern.compile("(.+) -> (.+)");
  }

  @Override
  public void execute(String inputLine, Output output) {
    Matcher matcher = getPattern()
        .matcher(inputLine.trim());

    if (matcher.matches()) {
      String userName = matcher.group(1).trim();
      String message = matcher.group(2).trim();

      postMessageUseCase.postMessage(userName, message);
    }
  }
}
