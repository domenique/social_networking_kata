package io.tripled.social.client.presentation.controller

import io.tripled.social.client.application.ReadWallUseCase
import io.tripled.social.client.presentation.Controller
import io.tripled.social.client.presentation.Output
import io.tripled.social.client.presentation.request.WallCliRequest

class WallController(private val readWallUseCase: ReadWallUseCase) : Controller<WallCliRequest> {
    override fun execute(request: WallCliRequest, output: Output) {
        output.print(readWallUseCase.readWall(request.userName))
    }
}
