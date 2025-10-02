package io.tripled.social.client.infrastructure.cli.controller

import io.tripled.social.client.application.PostMessage
import io.tripled.social.client.infrastructure.cli.Controller
import io.tripled.social.client.infrastructure.cli.Output
import io.tripled.social.client.infrastructure.cli.request.PostCliRequest

class PostController(private val postMessage: PostMessage) : Controller<PostCliRequest> {
    override fun execute(request: PostCliRequest, output: Output) {
        output.print(postMessage.postMessage(request.userName, request.message))
    }
}
