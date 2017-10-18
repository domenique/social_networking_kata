package io.tripled.social.client.presentation.request;

import io.tripled.social.client.presentation.Output;
import io.tripled.social.client.presentation.request.FollowCliRequest;
import io.tripled.social.client.presentation.request.PostCliRequest;
import io.tripled.social.client.presentation.request.ReadCliRequest;
import io.tripled.social.client.presentation.request.WallCliRequest;

public interface Visitor {

  void visit(FollowCliRequest request, Output output);

  void visit(PostCliRequest request, Output output);

  void visit(ReadCliRequest request, Output output);

  void visit(WallCliRequest request, Output output);
}
