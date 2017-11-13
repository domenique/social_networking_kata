package io.tripled.social.client.presentation.request;

import io.tripled.social.client.presentation.Output;

public interface Visitor {

  void visit(FollowCliRequest request, Output output);

  void visit(PostCliRequest request, Output output);

  void visit(ReadCliRequest request, Output output);

  void visit(WallCliRequest request, Output output);
}
