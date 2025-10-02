package io.tripled.social.client.infrastructure.cli.request

import io.tripled.social.client.infrastructure.cli.CliRequest
import io.tripled.social.client.infrastructure.cli.Output

class PostCliRequest(val userName: String, val message: String) : CliRequest {
    override fun accept(visitor: Visitor, output: Output) {
        visitor.visit(this, output)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false

        val request = other as PostCliRequest

        if (this.userName != request.userName) return false
        return message == request.message
    }

    override fun hashCode(): Int {
        var result = userName.hashCode()
        result = 31 * result + message.hashCode()
        return result
    }
}
