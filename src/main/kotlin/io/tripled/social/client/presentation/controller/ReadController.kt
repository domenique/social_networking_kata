package io.tripled.social.client.presentation.controller

import io.tripled.social.client.application.ReadMessagesUseCase
import io.tripled.social.client.presentation.Controller
import io.tripled.social.client.presentation.Output
import io.tripled.social.client.presentation.request.ReadCliRequest

class ReadController(private val readMessagesApplicationService: ReadMessagesUseCase) : Controller<ReadCliRequest> {
    override fun execute(request: ReadCliRequest, output: Output) {
        output.print(readMessagesApplicationService.readMessage(request.userName))
    }
}
