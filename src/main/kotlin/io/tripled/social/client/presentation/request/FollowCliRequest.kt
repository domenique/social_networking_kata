package io.tripled.social.client.presentation.request

import io.tripled.social.client.presentation.CliRequest
import io.tripled.social.client.presentation.Output

class FollowCliRequest(val userName: String, val userNameToFollow: String) : CliRequest {
    override fun accept(visitor: Visitor, output: Output) {
        visitor.visit(this, output)
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false

        val request = o as FollowCliRequest

        if (userName != request.userName) return false
        return userNameToFollow == request.userNameToFollow
    }

    override fun hashCode(): Int {
        var result = userName.hashCode()
        result = 31 * result + userNameToFollow.hashCode()
        return result
    }
}
