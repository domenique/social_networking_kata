package io.tripled.social.client.domain

import java.time.LocalDateTime

data class Message(
    private val userName: UserName,
    private val message: String,
    private val localDateTime: LocalDateTime
) : Comparable<Message> {

    constructor(userName: UserName, message: String, dateTimeProvider: DateTimeProvider) :
            this(userName, message, dateTimeProvider.now())

    fun getUserName(): UserName = userName
    fun getMessage(): String = message
    fun getTimeStamp(): LocalDateTime = localDateTime

    fun printWith(printer: MessagePrinter, dateTimeProvider: DateTimeProvider): String =
        printer.print(dateTimeProvider, userName.getUserName(), message, localDateTime)

    fun isWrittenBy(userName: UserName): Boolean = this.userName == userName

    override fun compareTo(other: Message): Int =
        other.localDateTime.compareTo(localDateTime)
}