package io.tripled.social.client.domain

import java.time.LocalDateTime
import java.time.ZoneId

class MessagePrinter private constructor(private val pattern: String) {

    companion object {
        fun ofPattern(pattern: String): MessagePrinter = MessagePrinter(pattern)
    }

    fun print(dateTimeProvider: DateTimeProvider, userName: String, messageBody: String, timeStamp: LocalDateTime): String =
        pattern
            .replace("%un", userName)
            .replace("%msg", messageBody)
            .replace("%t", createTime(timeStamp, dateTimeProvider.now()))

    private fun createTime(timeStamp: LocalDateTime, now: LocalDateTime): String {
        var qta = diffInSeconds(timeStamp, now)
        var unit = "second"

        if (qta >= 60) {
            unit = "minute"
            qta = qta / 60 % 60
        }

        if (qta > 1) {
            unit += "s"
        }

        return "$qta $unit ago"
    }

    private fun diffInSeconds(timeStamp: LocalDateTime, now: LocalDateTime): Long {
        val diffMillis = now.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() -
                timeStamp.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
        return diffMillis / 1000
    }
}