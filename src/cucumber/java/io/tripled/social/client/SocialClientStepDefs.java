package io.tripled.social.client;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.tripled.social.client.application.DefaultFollowUserUseCase;
import io.tripled.social.client.application.DefaultPostMessageUseCase;
import io.tripled.social.client.application.DefaultReadMessagesUseCase;
import io.tripled.social.client.application.DefaultReadWallUseCase;
import io.tripled.social.client.infrastructure.InMemorySocialNetworkRepository;
import io.tripled.social.client.presentation.ReadEvalPrintLoop;
import io.tripled.social.client.presentation.controller.FollowController;
import io.tripled.social.client.presentation.controller.PostController;
import io.tripled.social.client.presentation.controller.ReadController;
import io.tripled.social.client.presentation.controller.WallController;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SocialClientStepDefs {

  private CucumberInput input;
  private CucumberOutput output;
  private CucumberDateTimeProvider dateTimeProvider;

  @Before
  public void beforeScenario() {
    input = new CucumberInput();
    output = new CucumberOutput();

    dateTimeProvider = new CucumberDateTimeProvider(Clock.fixed(Instant.now(), ZoneId.systemDefault()));
    InMemorySocialNetworkRepository socialNetworkRepository = new InMemorySocialNetworkRepository(dateTimeProvider);

    ReadEvalPrintLoop repl = new ReadEvalPrintLoop(input, output, Arrays.asList(
        new PostController(new DefaultPostMessageUseCase(socialNetworkRepository, dateTimeProvider)),
        new ReadController(new DefaultReadMessagesUseCase(socialNetworkRepository)),
        new FollowController(new DefaultFollowUserUseCase()),
        new WallController(new DefaultReadWallUseCase())
    ));

    Thread thread = new Thread(repl);
    thread.start();
  }

  @After
  public void afterScenario() {
    input.addInput("\\q");

  }

  @Given("^\"([^\"]*)\" sends message \"([^\"]*)\"$")
  public void sendsMessage(String userName, String message) throws Throwable {
    input.addInput(userName + " -> " + message);
  }

  @When("^the user reads the timeline of \"([^\"]*)\"$")
  public void theUserReadsTheTimelineOf(String userName) throws Throwable {
    input.addInput(userName);
  }

  @Then("^the system responds with$")
  public void theSystemRespondsWith(String expectedResponse) throws Throwable {
    String lines[] = expectedResponse.split("\\r?\\n");
    output.assertContains(lines);
  }

  @Given("^\"([^\"]*)\" follows \"([^\"]*)\"$")
  public void follows(String userName, String follower) throws Throwable {
    input.addInput(userName + " follows " + follower);
  }

  @When("^the user reads the wall of \"([^\"]*)\"$")
  public void theUserReadsTheWallOf(String userName) throws Throwable {
    input.addInput(userName + " wall");
  }

  @And("^(\\d+) seconds pass by$")
  public void secondsPassBy(int seconds) throws Throwable {
    dateTimeProvider.fixateWithOffset(Duration.ofSeconds(seconds));
  }
}
