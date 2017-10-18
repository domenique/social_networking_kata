package io.tripled.social.client.presentation.controller;

import io.tripled.social.client.application.FollowUserUseCase;
import io.tripled.social.client.presentation.Controller;
import io.tripled.social.client.presentation.Output;
import io.tripled.social.client.presentation.request.FollowCliRequest;

public class FollowController implements Controller<FollowCliRequest> {

  private FollowUserUseCase followUserUseCase;

  public FollowController(FollowUserUseCase followUserUseCase) {
    this.followUserUseCase = followUserUseCase;
  }

  public void execute(FollowCliRequest request, Output output) {
    output.print(followUserUseCase.follow(request.getUserName(), request.getUserNameToFollow()));
  }


}
