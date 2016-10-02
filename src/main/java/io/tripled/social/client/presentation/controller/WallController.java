package io.tripled.social.client.presentation.controller;

import io.tripled.social.client.application.ReadWallUseCase;
import io.tripled.social.client.presentation.Output;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WallController extends AbstractController {
  private ReadWallUseCase readWallUseCase;

  public WallController(ReadWallUseCase readWallUseCase) {
    this.readWallUseCase = readWallUseCase;
  }

  @Override
  protected Pattern getPattern() {
    return Pattern.compile("(.+) wall");
  }

  @Override
  public void execute(String inputLine, Output output) {
    Matcher matcher = getPattern()
        .matcher(inputLine.trim());

    if (matcher.matches()) {
      String userName = matcher.group(1).trim();

      output.print(readWallUseCase.readWall(userName));
    }
  }
}
