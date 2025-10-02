package io.tripled.social.client.infrastructure.cli.controller

import io.tripled.social.client.application.ReadMessages
import io.tripled.social.client.infrastructure.cli.Controller
import io.tripled.social.client.infrastructure.cli.Output
import io.tripled.social.client.infrastructure.cli.request.ReadCliRequest

class ReadController(private val readMessagesApplicationService: ReadMessages) : Controller<ReadCliRequest> {
    override fun execute(request: ReadCliRequest, output: Output) {
        output.print(readMessagesApplicationService.readMessage(request.userName))
    }
}
