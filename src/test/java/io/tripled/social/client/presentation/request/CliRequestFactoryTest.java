package io.tripled.social.client.presentation.request;

import io.tripled.social.client.presentation.CliRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;

class CliRequestFactoryTest {

  private CliRequestFactory factory;

  @BeforeEach
  void setUp() throws Exception {
    factory = new CliRequestFactory();
  }

  @Test
  void returnsPostCommand() throws Exception {
    String input = "Alice -> Hi there!";

    CliRequest request = factory.create(input).orElse(null);

    assertThat(request, instanceOf(PostCliRequest.class));
  }

  @Test
  void returnsPostCommandWithTrimmedValues() throws Exception {
    String input = "  Alice   ->   Hi There!  ";

    CliRequest request = factory.create(input).orElse(null);

    assertThat(request, instanceOf(PostCliRequest.class));
    assertThat(request, equalTo(new PostCliRequest("Alice", "Hi There!")));
  }

  @Test
  void returnsReadCommand() throws Exception {
    String input = "Alice";
    String message = "  Alice  ";

    CliRequest request = factory.create(input).orElse(null);

    assertThat(request, instanceOf(ReadCliRequest.class));
  }

  @Test
  void returnsReadCommandWithTrimmedValues() throws Exception {
    String input = "  Alice  ";

    CliRequest request = factory.create(input).orElse(null);

    assertThat(request, instanceOf(ReadCliRequest.class));
    assertThat(request, equalTo(new ReadCliRequest("Alice")));
  }

  @Test
  void returnsFollowCommand() throws Exception {
    String input = "Alice follows Ben";

    CliRequest cliRequest = factory.create(input).orElse(null);

    assertThat(cliRequest, instanceOf(FollowCliRequest.class));
  }

  @Test
  void retunsWallCommand() throws Exception {
    String input = "Ben wall";

    CliRequest cliRequest = factory.create(input).orElse(null);

    assertThat(cliRequest, instanceOf(WallCliRequest.class));
  }
}