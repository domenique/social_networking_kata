package io.tripled.social.client.presentation.controller;

import io.tripled.social.client.application.ReadWallUseCase;
import io.tripled.social.client.presentation.Controller;
import io.tripled.social.client.presentation.Output;
import io.tripled.social.client.presentation.request.WallCliRequest;

public class WallController implements Controller<WallCliRequest> {
  private ReadWallUseCase readWallUseCase;

  public WallController(ReadWallUseCase readWallUseCase) {
    this.readWallUseCase = readWallUseCase;
  }

  @Override
  public void execute(WallCliRequest request, Output output) {
    output.print(readWallUseCase.readWall(request.getUserName()));
  }
}
