package io.tripled.social.client.presentation.controller;

import io.tripled.social.client.application.FollowUserUseCase;
import io.tripled.social.client.presentation.Output;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FollowController extends AbstractController {

  private FollowUserUseCase followUserUseCase;

  public FollowController(FollowUserUseCase followUserUseCase) {
    this.followUserUseCase = followUserUseCase;
  }

  @Override
  protected Pattern getPattern() {
    return Pattern.compile("(.+) follows (.+)");
  }

  @Override
  public void execute(String inputLine, Output output) {
    Matcher matcher = getPattern()
        .matcher(inputLine.trim());

    if (matcher.matches()) {
      String userName = matcher.group(1).trim();
      String userNameToFollow = matcher.group(2).trim();

      followUserUseCase.follow(userName, userNameToFollow);
    }
  }
}
