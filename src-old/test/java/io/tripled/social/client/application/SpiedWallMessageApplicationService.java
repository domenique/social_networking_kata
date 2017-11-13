package io.tripled.social.client.application;

public class SpiedWallMessageApplicationService implements ReadWallUseCase {

  public String userName;

  @Override
  public String readWall(String userName) {
    this.userName = userName;
    return userName;
  }
}
