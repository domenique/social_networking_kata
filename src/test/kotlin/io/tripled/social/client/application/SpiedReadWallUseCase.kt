package io.tripled.social.client.application

class SpiedReadWallUseCase : ReadWallUseCase {
    @JvmField
    var userName: String? = null

    override fun readWall(userName: String): String {
        this.userName = userName
        return userName
    }
}
