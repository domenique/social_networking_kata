package io.tripled.social.client.domain

interface Messages {
    fun findAll(): List<Message>
    fun save(message: Message)
    fun findAllByUserName(userName: UserName): List<Message>
    fun findAllByUserNames(userNames: List<UserName>): List<Message>
}