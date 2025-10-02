package io.tripled.social.client.application.usecase.spies

import io.tripled.social.client.application.ReadMessages

class ReadMessagesSpy(
    var userName: String? = null
) : ReadMessages {

    override fun readMessage(userName: String): String {
        this.userName = userName
        return "null"
    }
}
