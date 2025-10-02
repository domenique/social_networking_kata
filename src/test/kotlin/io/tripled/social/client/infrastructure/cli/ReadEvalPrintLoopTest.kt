package io.tripled.social.client.infrastructure.cli

import io.tripled.social.client.application.usecase.spies.FollowUserSpy
import io.tripled.social.client.application.usecase.spies.PostMessageSpy
import io.tripled.social.client.application.usecase.spies.ReadMessagesSpy
import io.tripled.social.client.application.usecase.spies.ReadWallSpy
import io.tripled.social.client.infrastructure.cli.controller.FollowController
import io.tripled.social.client.infrastructure.cli.controller.PostController
import io.tripled.social.client.infrastructure.cli.controller.ReadController
import io.tripled.social.client.infrastructure.cli.controller.WallController
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test

internal class ReadEvalPrintLoopTest {
    private val input: TestInput = TestInput()
    private val output: TestOutput = TestOutput()
    private val repl: ReadEvalPrintLoop = ReadEvalPrintLoop(
        input, output,
        PostController(PostMessageSpy()),
        ReadController(ReadMessagesSpy()),
        WallController(ReadWallSpy()),
        FollowController(FollowUserSpy())
    )

    @Test
    fun quits() {
        input.addInput("\\q")

        repl.run()

        MatcherAssert.assertThat<Boolean?>(repl.isStopped, Matchers.`is`<Boolean?>(true))
        output.assertIsEmpty()
    }
}
