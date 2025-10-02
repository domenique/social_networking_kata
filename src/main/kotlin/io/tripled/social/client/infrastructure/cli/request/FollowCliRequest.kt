package io.tripled.social.client.infrastructure.cli.request

import io.tripled.social.client.infrastructure.cli.CliRequest
import io.tripled.social.client.infrastructure.cli.Output

class FollowCliRequest(val userName: String, val userNameToFollow: String) : CliRequest {
    override fun accept(visitor: Visitor, output: Output) {
        visitor.visit(this, output)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false

        val request = other as FollowCliRequest

        if (userName != request.userName) return false
        return userNameToFollow == request.userNameToFollow
    }

    override fun hashCode(): Int {
        var result = userName.hashCode()
        result = 31 * result + userNameToFollow.hashCode()
        return result
    }
}
