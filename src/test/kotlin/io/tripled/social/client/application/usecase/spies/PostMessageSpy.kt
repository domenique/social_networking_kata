package io.tripled.social.client.application.usecase.spies

import io.tripled.social.client.application.PostMessage

class PostMessageSpy(
    var userName: String? = null,
    var message: String? = null
) : PostMessage {

    override fun postMessage(userName: String, message: String): String {
        this.userName = userName
        this.message = message

        return "$userName posted: $message"
    }
}
