package io.tripled.social.client.application

interface ReadWallUseCase {
    fun readWall(userName: String): String
}
