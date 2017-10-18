package io.tripled.social.client.presentation.request;

import io.tripled.social.client.presentation.Output;
import io.tripled.social.client.presentation.controller.FollowController;
import io.tripled.social.client.presentation.controller.PostController;
import io.tripled.social.client.presentation.controller.ReadController;
import io.tripled.social.client.presentation.controller.WallController;

public class RequestDispatcher implements Visitor {
  private final PostController postController;
  private final ReadController readController;
  private final WallController wallController;
  private final FollowController followController;


  public RequestDispatcher(PostController postController,
                           ReadController readController,
                           WallController wallController,
                           FollowController followController) {
    this.postController = postController;
    this.readController = readController;
    this.wallController = wallController;
    this.followController = followController;
  }

  @Override
  public void visit(FollowCliRequest request, Output output) {
    followController.execute(request, output);
  }

  @Override
  public void visit(PostCliRequest request, Output output) {
    postController.execute(request, output);
  }

  @Override
  public void visit(ReadCliRequest request, Output output) {
    readController.execute(request, output);
  }

  @Override
  public void visit(WallCliRequest request, Output output) {
    wallController.execute(request, output);
  }
}