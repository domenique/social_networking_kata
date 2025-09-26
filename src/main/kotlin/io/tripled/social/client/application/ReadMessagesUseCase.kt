package io.tripled.social.client.application

interface ReadMessagesUseCase {
    fun readMessage(userName: String): String
}
