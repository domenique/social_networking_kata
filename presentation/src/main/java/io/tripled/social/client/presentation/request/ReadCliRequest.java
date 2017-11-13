package io.tripled.social.client.presentation.request;

import io.tripled.social.client.presentation.CliRequest;
import io.tripled.social.client.presentation.Output;

public class ReadCliRequest implements CliRequest {
  private String userName;

  public ReadCliRequest(String userName) {
    this.userName = userName;
  }

  @Override
  public void accept(Visitor visitor, Output output) {
    visitor.visit(this, output);
  }

  public String getUserName() {
    return userName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ReadCliRequest request = (ReadCliRequest) o;

    return userName.equals(request.userName);
  }

  @Override
  public int hashCode() {
    return userName.hashCode();
  }
}
