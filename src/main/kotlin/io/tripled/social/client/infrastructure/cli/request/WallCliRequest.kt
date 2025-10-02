package io.tripled.social.client.infrastructure.cli.request

import io.tripled.social.client.infrastructure.cli.CliRequest
import io.tripled.social.client.infrastructure.cli.Output

class WallCliRequest(val userName: String) : CliRequest {
    override fun accept(visitor: Visitor, output: Output) {
        visitor.visit(this, output)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false

        val request = other as WallCliRequest

        return userName == request.userName
    }

    override fun hashCode(): Int {
        return userName.hashCode()
    }
}
