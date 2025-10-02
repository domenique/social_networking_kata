package io.tripled.social.client.application.usecase.spies

import io.tripled.social.client.application.ReadWall

class ReadWallSpy(
    var userName: String? = null
) : ReadWall {

    override fun readWall(userName: String): String {
        this.userName = userName
        return userName
    }
}
