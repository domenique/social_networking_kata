package io.tripled.social.client.presentation.request;

import io.tripled.social.client.presentation.CliRequest;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

public class CliRequestFactoryTest {

  private CliRequestFactory factory;

  @Before
  public void setUp() throws Exception {
    factory = new CliRequestFactory();
  }

  @Test
  public void returnsPostCommand() throws Exception {
    String input = "Alice -> Hi there!";

    CliRequest request = factory.create(input).orElse(null);

    assertThat(request, instanceOf(PostCliRequest.class));
  }

  @Test
  public void returnsPostCommandWithTrimmedValues() throws Exception {
    String input = "  Alice   ->   Hi There!  ";

    CliRequest request = factory.create(input).orElse(null);

    assertThat(request, instanceOf(PostCliRequest.class));
    assertThat(request, equalTo(new PostCliRequest("Alice", "Hi There!")));
  }

  @Test
  public void returnsReadCommand() throws Exception {
    String input = "Alice";
    String message = "  Alice  ";

    CliRequest request = factory.create(input).orElse(null);

    assertThat(request, instanceOf(ReadCliRequest.class));
  }

  @Test
  public void returnsReadCommandWithTrimmedValues() throws Exception {
    String input = "  Alice  ";

    CliRequest request = factory.create(input).orElse(null);

    assertThat(request, instanceOf(ReadCliRequest.class));
    assertThat(request, equalTo(new ReadCliRequest("Alice")));
  }

  @Test
  public void returnsFollowCommand() throws Exception {
    String input = "Alice follows Ben";

    CliRequest cliRequest = factory.create(input).orElse(null);

    assertThat(cliRequest, instanceOf(FollowCliRequest.class));
  }

  @Test
  public void retunsWallCommand() throws Exception {
    String input = "Ben wall";

    CliRequest cliRequest = factory.create(input).orElse(null);

    assertThat(cliRequest, instanceOf(WallCliRequest.class));
  }
}