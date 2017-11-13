package io.tripled.social.client;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class HelloWorldStepDefs {

  private String myName;
  private String response;

  @Given("^the system knows my name is \"(.*)\"$")
  public void theSystemKnowsMyNameIs(String name) throws Throwable {
    this.myName = name;
  }

  @When("^I say hi to the system$")
  public void iSayHiToTheSystem() throws Throwable {
    response = "Hello, " + myName;
  }

  @Then("^the system responds with \"(.*)\"$")
  public void theSystemRespondsWith(String expectedResponse) throws Throwable {
    assertThat(response, equalTo(expectedResponse));
  }
}
