package io.tripled.social.client.presentation.controller

import io.tripled.social.client.application.PostMessageUseCase
import io.tripled.social.client.presentation.Controller
import io.tripled.social.client.presentation.Output
import io.tripled.social.client.presentation.request.PostCliRequest

class PostController(private val postMessageUseCase: PostMessageUseCase) : Controller<PostCliRequest> {
    override fun execute(request: PostCliRequest, output: Output) {
        output.print(postMessageUseCase.postMessage(request.userName, request.message))
    }
}
