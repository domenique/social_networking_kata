package io.tripled.social.client.presentation

import io.tripled.social.client.application.SpiedFollowUserUseCase
import io.tripled.social.client.application.SpiedPostMessageUseCase
import io.tripled.social.client.application.SpiedReadMessagesUseCase
import io.tripled.social.client.application.SpiedReadWallUseCase
import io.tripled.social.client.presentation.controller.FollowController
import io.tripled.social.client.presentation.controller.PostController
import io.tripled.social.client.presentation.controller.ReadController
import io.tripled.social.client.presentation.controller.WallController
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test

internal class ReadEvalPrintLoopTest {
    private val input: TestInput = TestInput()
    private val output: TestOutput = TestOutput()
    private val repl: ReadEvalPrintLoop = ReadEvalPrintLoop(
        input, output,
        PostController(SpiedPostMessageUseCase()),
        ReadController(SpiedReadMessagesUseCase()),
        WallController(SpiedReadWallUseCase()),
        FollowController(SpiedFollowUserUseCase())
    )

    @Test
    fun quits() {
        input.addInput("\\q")

        repl.run()

        MatcherAssert.assertThat<Boolean?>(repl.isStopped, Matchers.`is`<Boolean?>(true))
        output.assertIsEmpty()
    }
}
