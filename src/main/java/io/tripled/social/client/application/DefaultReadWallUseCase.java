package io.tripled.social.client.application;

public class DefaultReadWallUseCase implements ReadWallUseCase {
  @Override
  public String readWall(String userName) {
    System.out.println("timeline of " + userName + " will be read");
    return null;
  }
}
