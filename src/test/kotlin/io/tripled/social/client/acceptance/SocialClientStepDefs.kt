package io.tripled.social.client.acceptance

import io.cucumber.java.After
import io.cucumber.java.Before
import io.cucumber.java.en.And
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import io.tripled.social.client.application.DefaultFollowUserUseCase
import io.tripled.social.client.application.DefaultPostMessageUseCase
import io.tripled.social.client.application.DefaultReadMessagesUseCase
import io.tripled.social.client.application.DefaultReadWallUseCase
import io.tripled.social.client.infrastructure.InMemorySocialNetworkRepository
import io.tripled.social.client.presentation.ReadEvalPrintLoop
import io.tripled.social.client.presentation.controller.FollowController
import io.tripled.social.client.presentation.controller.PostController
import io.tripled.social.client.presentation.controller.ReadController
import io.tripled.social.client.presentation.controller.WallController
import java.time.Clock
import java.time.Duration
import java.time.Instant
import java.time.ZoneId

class SocialClientStepDefs {
    private val input: CucumberInput = CucumberInput()
    private val output: CucumberOutput = CucumberOutput()
    private val dateTimeProvider: CucumberDateTimeProvider = CucumberDateTimeProvider(Clock.fixed(Instant.now(), ZoneId.systemDefault()))

    @Before
    fun beforeScenario() {
        val socialNetworkRepository = InMemorySocialNetworkRepository(dateTimeProvider)
        val repl = ReadEvalPrintLoop(
            input, output,
            PostController(DefaultPostMessageUseCase(socialNetworkRepository, dateTimeProvider)),
            ReadController(DefaultReadMessagesUseCase(socialNetworkRepository)),
            WallController(DefaultReadWallUseCase(socialNetworkRepository)),
            FollowController(DefaultFollowUserUseCase(socialNetworkRepository))
        )

        val thread = Thread(repl)
        thread.start()
    }

    @After
    fun afterScenario() {
        input.sendInput("\\q")
    }

    @Given("^\"([^\"]*)\" sends message \"([^\"]*)\"$")
    fun sendsMessage(userName: String, message: String) {
        input.sendInput(userName + " -> " + message)
        output.assertContains(userName + " posted: " + message)
    }

    @When("^the user reads the timeline of \"([^\"]*)\"$")
    fun theUserReadsTheTimelineOf(userName: String) {
        input.sendInput(userName)
    }

    @Then("^the system responds with$")
    fun theSystemRespondsWith(expectedResponse: String) {
        val lines: Array<String> =
            expectedResponse.split("\\r?\\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        output.assertContains(*lines)
    }

    @Given("^\"([^\"]*)\" follows \"([^\"]*)\"$")
    fun follows(userName: String, follower: String) {
        input.sendInput(userName + " follows " + follower)
        output.assertContains(userName + " will follow " + follower)
    }

    @When("^the user reads the wall of \"([^\"]*)\"$")
    fun theUserReadsTheWallOf(userName: String) {
        input.sendInput(userName + " wall")
    }

    @And("^(\\d+) seconds pass by$")
    fun secondsPassBy(seconds: Int) {
        dateTimeProvider.fixateWithOffset(Duration.ofSeconds(seconds.toLong()))
    }
}
