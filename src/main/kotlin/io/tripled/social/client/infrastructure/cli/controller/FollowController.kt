package io.tripled.social.client.infrastructure.cli.controller

import io.tripled.social.client.application.FollowUser
import io.tripled.social.client.infrastructure.cli.Controller
import io.tripled.social.client.infrastructure.cli.Output
import io.tripled.social.client.infrastructure.cli.request.FollowCliRequest

class FollowController(private val followUser: FollowUser) : Controller<FollowCliRequest> {
    override fun execute(request: FollowCliRequest, output: Output) {
        output.print(followUser.follow(request.userName, request.userNameToFollow))
    }
}
