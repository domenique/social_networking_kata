package io.tripled.social.client.presentation.request;

import io.tripled.social.client.presentation.CliRequest;
import io.tripled.social.client.presentation.Output;

public class PostCliRequest implements CliRequest {
  private String UserName;
  private String message;

  public PostCliRequest(String userName, String message) {
    UserName = userName;
    this.message = message;
  }

  @Override
  public void accept(Visitor visitor, Output output) {
    visitor.visit(this, output);
  }

  public String getUserName() {
    return UserName;
  }

  public String getMessage() {
    return message;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    PostCliRequest request = (PostCliRequest) o;

    if (!UserName.equals(request.UserName)) return false;
    return message.equals(request.message);
  }

  @Override
  public int hashCode() {
    int result = UserName.hashCode();
    result = 31 * result + message.hashCode();
    return result;
  }
}
