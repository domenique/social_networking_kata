package io.tripled.social.client.presentation.controller

import io.tripled.social.client.application.FollowUserUseCase
import io.tripled.social.client.presentation.Controller
import io.tripled.social.client.presentation.Output
import io.tripled.social.client.presentation.request.FollowCliRequest

class FollowController(private val followUserUseCase: FollowUserUseCase) : Controller<FollowCliRequest> {
    override fun execute(request: FollowCliRequest, output: Output) {
        output.print(followUserUseCase.follow(request.userName, request.userNameToFollow))
    }
}
