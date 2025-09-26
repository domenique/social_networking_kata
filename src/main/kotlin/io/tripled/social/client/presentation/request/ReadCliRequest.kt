package io.tripled.social.client.presentation.request

import io.tripled.social.client.presentation.CliRequest
import io.tripled.social.client.presentation.Output

class ReadCliRequest(val userName: String) : CliRequest {
    override fun accept(visitor: Visitor, output: Output) {
        visitor.visit(this, output)
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false

        val request = o as ReadCliRequest

        return userName == request.userName
    }

    override fun hashCode(): Int {
        return userName.hashCode()
    }
}
