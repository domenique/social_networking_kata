package io.tripled.social.client.presentation.request;

import io.tripled.social.client.presentation.Output;

public interface Visitable {

  void accept(Visitor visitor, Output output);

}
