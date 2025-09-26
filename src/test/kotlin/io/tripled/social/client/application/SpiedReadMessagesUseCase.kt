package io.tripled.social.client.application

class SpiedReadMessagesUseCase : ReadMessagesUseCase {
    @JvmField
    var userName: String? = null

    override fun readMessage(userName: String): String {
        this.userName = userName
        return "null"
    }
}
