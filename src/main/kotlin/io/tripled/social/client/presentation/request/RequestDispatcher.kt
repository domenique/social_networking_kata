package io.tripled.social.client.presentation.request

import io.tripled.social.client.presentation.Output
import io.tripled.social.client.presentation.controller.FollowController
import io.tripled.social.client.presentation.controller.PostController
import io.tripled.social.client.presentation.controller.ReadController
import io.tripled.social.client.presentation.controller.WallController

class RequestDispatcher(
    private val postController: PostController,
    private val readController: ReadController,
    private val wallController: WallController,
    private val followController: FollowController
) : Visitor {
    override fun visit(request: FollowCliRequest, output: Output) {
        followController.execute(request, output)
    }

    override fun visit(request: PostCliRequest, output: Output) {
        postController.execute(request, output)
    }

    override fun visit(request: ReadCliRequest, output: Output) {
        readController.execute(request, output)
    }

    override fun visit(request: WallCliRequest, output: Output) {
        wallController.execute(request, output)
    }
}