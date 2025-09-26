package io.tripled.social.client.presentation.request

import io.tripled.social.client.presentation.Output

interface Visitor {
    fun visit(request: FollowCliRequest, output: Output)

    fun visit(request: PostCliRequest, output: Output)

    fun visit(request: ReadCliRequest, output: Output)

    fun visit(request: WallCliRequest, output: Output)
}
