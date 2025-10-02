package io.tripled.social.client.infrastructure.inmemory

import io.tripled.social.client.domain.Message
import io.tripled.social.client.domain.Messages
import io.tripled.social.client.domain.UserName
import java.util.*
import java.util.Collections.unmodifiableList
import java.util.stream.Collectors

class InMemoryMessages : Messages {
    private val messages: MutableList<Message> = ArrayList<Message>()

    override fun findAll(): List<Message> = unmodifiableList(messages)

    override fun save(message: Message) {
        this.messages.add(message)
    }

    override fun findAllByUserName(userName: UserName): List<Message> {
        return messages.stream()
            .filter { m: Message? -> m!!.isWrittenBy(userName) }
            .sorted()
            .collect(Collectors.toList())
    }

    override fun findAllByUserNames(userNames: List<UserName>): List<Message> {
        return messages.stream()
            .filter { m: Message? ->
                for (userName in userNames) {
                    if (m!!.isWrittenBy(userName)) {
                        return@filter true
                    }
                }
                false
            }
            .sorted()
            .collect(Collectors.toList())
    }
}
