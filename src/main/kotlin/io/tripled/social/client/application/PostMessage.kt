package io.tripled.social.client.application

interface PostMessage {
    fun postMessage(userName: String, message: String): String
}
