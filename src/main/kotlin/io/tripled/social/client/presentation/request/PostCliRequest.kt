package io.tripled.social.client.presentation.request

import io.tripled.social.client.presentation.CliRequest
import io.tripled.social.client.presentation.Output

class PostCliRequest(val userName: String, val message: String) : CliRequest {
    override fun accept(visitor: Visitor, output: Output) {
        visitor.visit(this, output)
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false

        val request = o as PostCliRequest

        if (this.userName != request.userName) return false
        return message == request.message
    }

    override fun hashCode(): Int {
        var result = userName.hashCode()
        result = 31 * result + message.hashCode()
        return result
    }
}
