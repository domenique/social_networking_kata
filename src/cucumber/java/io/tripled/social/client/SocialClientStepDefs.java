package io.tripled.social.client;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.tripled.social.client.presentation.ReadEvalPrintLoop;

import java.util.Collections;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SocialClientStepDefs {

  private ReadEvalPrintLoop repl;
  private CucumberInput input;
  private CucumberOutput output;

  @Before
  public void beforeScenario() {
    input = new CucumberInput();
    output = new CucumberOutput();
    repl = new ReadEvalPrintLoop(input, output, Collections.emptyList());
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
    input.addInput("\\q");

    repl.start();

    assertThat(repl.isStopped(), is(true));
    assertResponse(expectedResponse);
  }

  @Given("^\"([^\"]*)\" follows \"([^\"]*)\"$")
  public void follows(String userName, String follower) throws Throwable {
    input.addInput(userName + " follows " + follower);
  }

  @When("^the user reads the wall of \"([^\"]*)\"$")
  public void theUserReadsTheWallOf(String userName) throws Throwable {
    input.addInput(userName + " wall");
  }

  private void assertResponse(String expectedResponse) {
    String lines[] = expectedResponse.split("\\r?\\n");
    output.assertSize(lines.length);
    for (String line : lines) {
      output.assertContains(equalTo(line));
    }
  }
}
