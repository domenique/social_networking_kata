package io.tripled.social.client;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class SocialClientStepDefs {

  @Given("^\"([^\"]*)\" sends message \"([^\"]*)\"$")
  public void sendsMessage(String userName, String message) throws Throwable {
    System.out.println("username: " + userName + " message: " + message);
  }

  @When("^the user reads the timeline of \"([^\"]*)\"$")
  public void theUserReadsTheTimelineOf(String userName) throws Throwable {
    System.out.println("username: " + userName);
  }

  @Then("^the system responds with$")
  public void theSystemResponseWith(String expectedResponse) throws Throwable {
    System.out.println("expectedResponse: " + expectedResponse);
  }

  @Given("^\"([^\"]*)\" follows \"([^\"]*)\"$")
  public void follows(String userName, String follower) throws Throwable {
    System.out.println("username: " + userName + " follower: " + follower);
  }

  @When("^the user reads the wall of \"([^\"]*)\"$")
  public void theUserReadsTheWallOf(String userName) throws Throwable {
    System.out.println("username: " + userName);
  }
}
