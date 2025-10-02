package io.tripled.social.client.infrastructure.cli

import io.tripled.social.client.infrastructure.cli.controller.FollowController
import io.tripled.social.client.infrastructure.cli.controller.PostController
import io.tripled.social.client.infrastructure.cli.controller.ReadController
import io.tripled.social.client.infrastructure.cli.controller.WallController
import io.tripled.social.client.infrastructure.cli.request.CliRequestFactory
import io.tripled.social.client.infrastructure.cli.request.RequestDispatcher
import java.util.*

class ReadEvalPrintLoop(
    input: Input,
    output: Output,
    postController: PostController,
    readController: ReadController,
    wallController: WallController,
    followController: FollowController
) : Runnable {
    private val input: Input
    private val output: Output
    private val requestFactory: CliRequestFactory
    private val dispatcher: RequestDispatcher
    var isStopped: Boolean = false
        private set

    init {
        Objects.requireNonNull<Input?>(input, "the Input cannot be null")
        Objects.requireNonNull<Output?>(output, "the Output cannot be null")
        this.input = input
        this.output = output
        this.requestFactory = CliRequestFactory()
        this.dispatcher = RequestDispatcher(postController, readController, wallController, followController)
    }

    override fun run() {
        this.isStopped = false
        while (!this.isStopped) {
            input.read()?.let {
                if (isQuitCommand(it)) {
                    this.isStopped = true
                    break
                }
                executeCommands(it)
            }
        }
    }

    private fun executeCommands(readLine: String) {
        requestFactory.create(readLine)?.accept(dispatcher, output)

    }

    private fun isQuitCommand(readLine: String): Boolean {
        return QUIT_COMMAND == readLine
    }

    companion object {
        private const val QUIT_COMMAND = "\\q"
    }
}
