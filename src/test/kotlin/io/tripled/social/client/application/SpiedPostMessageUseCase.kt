package io.tripled.social.client.application

class SpiedPostMessageUseCase : PostMessageUseCase {
    @JvmField
    var userName: String? = null
    @JvmField
    var message: String? = null

    override fun postMessage(userName: String, message: String): String {
        this.userName = userName
        this.message = message

        return userName + " posted: " + message
    }
}
