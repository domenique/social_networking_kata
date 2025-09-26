package io.tripled.social.client

import io.tripled.social.client.application.DefaultFollowUserUseCase
import io.tripled.social.client.application.DefaultPostMessageUseCase
import io.tripled.social.client.application.DefaultReadMessagesUseCase
import io.tripled.social.client.application.DefaultReadWallUseCase
import io.tripled.social.client.domain.DateTimeProvider
import io.tripled.social.client.infrastructure.InMemorySocialNetworkRepository
import io.tripled.social.client.infrastructure.SystemDateTimeProvider
import io.tripled.social.client.presentation.Input
import io.tripled.social.client.presentation.Output
import io.tripled.social.client.presentation.ReadEvalPrintLoop
import io.tripled.social.client.presentation.console.ConsoleInput
import io.tripled.social.client.presentation.console.ConsoleOutput
import io.tripled.social.client.presentation.controller.FollowController
import io.tripled.social.client.presentation.controller.PostController
import io.tripled.social.client.presentation.controller.ReadController
import io.tripled.social.client.presentation.controller.WallController
import java.io.BufferedReader
import java.io.InputStreamReader

object SocialNetworkApplication {
    @JvmStatic
    fun main(args: Array<String>) {
        val repl = createRepl()

        Thread(repl).start()
    }

    private fun createRepl(): ReadEvalPrintLoop {
        val input: Input = ConsoleInput(BufferedReader(InputStreamReader(System.`in`)))
        val output: Output = ConsoleOutput()

        val dateTimeProvider: DateTimeProvider = SystemDateTimeProvider()
        val socialNetworkRepository = InMemorySocialNetworkRepository(dateTimeProvider)

        return ReadEvalPrintLoop(
            input, output,
            PostController(DefaultPostMessageUseCase(socialNetworkRepository, dateTimeProvider)),
            ReadController(DefaultReadMessagesUseCase(socialNetworkRepository)),
            WallController(DefaultReadWallUseCase(socialNetworkRepository)),
            FollowController(DefaultFollowUserUseCase(socialNetworkRepository))
        )
    }
}
