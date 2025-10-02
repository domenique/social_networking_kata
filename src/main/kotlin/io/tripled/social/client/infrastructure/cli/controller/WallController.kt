package io.tripled.social.client.infrastructure.cli.controller

import io.tripled.social.client.application.ReadWall
import io.tripled.social.client.infrastructure.cli.Controller
import io.tripled.social.client.infrastructure.cli.Output
import io.tripled.social.client.infrastructure.cli.request.WallCliRequest

class WallController(private val readWall: ReadWall) : Controller<WallCliRequest> {
    override fun execute(request: WallCliRequest, output: Output) {
        output.print(readWall.readWall(request.userName))
    }
}
