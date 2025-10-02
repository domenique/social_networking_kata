package io.tripled.social.client.application

interface ReadMessages {
    fun readMessage(userName: String): String
}
