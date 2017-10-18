package io.tripled.social.client.presentation.request;

import io.tripled.social.client.presentation.CliRequest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CliRequestFactory {

  private final List<RequestFactory> factories = Arrays.asList(new PostCliRequestFactory(), new ReadCliRequestFactory(),
      new FollowCliRequestFactory(), new WallCliRequestFactory());

  public Optional<CliRequest> create(String input) {
    return factories.stream()
        .filter(i -> i.supports(input))
        .findFirst()
        .map(i -> i.create(input));
  }

  interface RequestFactory {

    boolean supports(String input);

    CliRequest create(String input);

  }

  private class PostCliRequestFactory implements RequestFactory {

    @Override
    public CliRequest create(String input) {
      Matcher matcher = getMatcher(input);
      if (matcher.matches()) {
        return new PostCliRequest(matcher.group(1).trim(), matcher.group(2).trim());
      }
      return null;
    }

    @Override
    public boolean supports(String input) {
      return input != null && getMatcher(input).matches();
    }

    private Matcher getMatcher(String inputLine) {
      return Pattern.compile("(.+) -> (.+)").matcher(inputLine.trim());
    }
  }

  private class ReadCliRequestFactory implements RequestFactory {

    @Override
    public CliRequest create(String input) {
      Matcher matcher = getMatcher(input);
      if (matcher.matches()) {
        return new ReadCliRequest(matcher.group(1).trim());
      }
      return null;
    }

    @Override
    public boolean supports(String input) {
      return input != null && getMatcher(input).matches();
    }

    private Matcher getMatcher(String inputLine) {
      return Pattern.compile("(\\w+)").matcher(inputLine.trim());
    }
  }

  private class FollowCliRequestFactory implements RequestFactory {

    @Override
    public CliRequest create(String input) {
      Matcher matcher = getMatcher(input);
      if (matcher.matches()) {
        return new FollowCliRequest(matcher.group(1).trim(), matcher.group(2).trim());
      }
      return null;
    }

    @Override
    public boolean supports(String input) {
      return input != null && getMatcher(input).matches();
    }

    private Matcher getMatcher(String inputLine) {
      return Pattern.compile("(.+) follows (.+)").matcher(inputLine.trim());
    }
  }

  private class WallCliRequestFactory implements RequestFactory {

    @Override
    public CliRequest create(String input) {
      Matcher matcher = getMatcher(input);
      if (matcher.matches()) {
        return new WallCliRequest(matcher.group(1).trim());
      }
      return null;
    }

    @Override
    public boolean supports(String input) {
      return input != null && getMatcher(input).matches();
    }

    private Matcher getMatcher(String inputLine) {
      return Pattern.compile("(.+) wall").matcher(inputLine.trim());
    }
  }
}
