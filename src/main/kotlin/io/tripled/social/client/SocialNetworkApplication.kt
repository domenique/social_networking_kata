package io.tripled.social.client

import io.tripled.social.client.application.usecase.FollowUserUseCase
import io.tripled.social.client.application.usecase.PostMessageUseCase
import io.tripled.social.client.application.usecase.ReadMessagesUseCase
import io.tripled.social.client.application.usecase.ReadWallUseCase
import io.tripled.social.client.domain.DateTimeProvider
import io.tripled.social.client.infrastructure.cli.Input
import io.tripled.social.client.infrastructure.cli.Output
import io.tripled.social.client.infrastructure.cli.ReadEvalPrintLoop
import io.tripled.social.client.infrastructure.cli.console.ConsoleInput
import io.tripled.social.client.infrastructure.cli.console.ConsoleOutput
import io.tripled.social.client.infrastructure.cli.controller.FollowController
import io.tripled.social.client.infrastructure.cli.controller.PostController
import io.tripled.social.client.infrastructure.cli.controller.ReadController
import io.tripled.social.client.infrastructure.cli.controller.WallController
import io.tripled.social.client.infrastructure.inmemory.InMemorySocialNetworkRepository
import io.tripled.social.client.infrastructure.time.SystemDateTimeProvider
import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val repl = createRepl()
    Thread(repl).start()
}

private fun createRepl(): ReadEvalPrintLoop {
    val input: Input = ConsoleInput(BufferedReader(InputStreamReader(System.`in`)))
    val output: Output = ConsoleOutput()

    val dateTimeProvider: DateTimeProvider = SystemDateTimeProvider()
    val socialNetworkRepository = InMemorySocialNetworkRepository(dateTimeProvider)

    return ReadEvalPrintLoop(
        input,
        output,
        PostController(PostMessageUseCase(socialNetworkRepository, dateTimeProvider)),
        ReadController(ReadMessagesUseCase(socialNetworkRepository)),
        WallController(ReadWallUseCase(socialNetworkRepository)),
        FollowController(FollowUserUseCase(socialNetworkRepository))
    )
}