package io.tripled.social.client.application

interface PostMessageUseCase {
    fun postMessage(userName: String, message: String): String
}
