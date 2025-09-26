package io.tripled.social.client.domain

data class UserName(private val userName: String) {
    fun getUserName(): String = userName

    override fun toString(): String = userName
}