package io.tripled.social.client.acceptance

import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers

class HelloWorldStepDefs {
    private var myName: String? = null
    private var response: String? = null

    @Given("^the system knows my name is \"(.*)\"$")
    fun theSystemKnowsMyNameIs(name: String?) {
        this.myName = name
    }

    @When("^I say hi to the system$")
    fun iSayHiToTheSystem() {
        response = "Hello, " + myName
    }

    @Then("^the system responds with \"(.*)\"$")
    fun theSystemRespondsWith(expectedResponse: String) {
        MatcherAssert.assertThat<String?>(response, Matchers.equalTo<String>(expectedResponse))
    }
}
