package io.tripled.social.client.domain

class SocialNetwork(
    private val messages: Messages,
    private val relationships: Relationships,
    private val dateTimeProvider: DateTimeProvider
) {
    companion object {
        private val READING_PRINTER = MessagePrinter.ofPattern("%msg (%t)")
        private val WALL_PRINTER = MessagePrinter.ofPattern("%un - %msg (%t)")
    }

    fun postMessage(message: Message): String {
        messages.save(message)
        return "${message.getUserName()} posted: ${message.getMessage()}"
    }

    fun read(userName: UserName): String {
        val messagesToPrint = messages.findAllByUserName(userName)
        return printAll(messagesToPrint, READING_PRINTER)
    }

    fun follow(userName: UserName, userNameToFollow: UserName): String {
        relationships.save(FollowingRelationship(userName, userNameToFollow))
        return "$userName will follow $userNameToFollow"
    }

    fun readWall(userName: UserName): String {
        val userNames = mutableListOf<UserName>().apply {
            add(userName)
            addAll(relationships.findRelationshipsFor(userName).map { it.getFollowing() })
        }

        val messagesToPrint = messages.findAllByUserNames(userNames)
        return printAll(messagesToPrint, WALL_PRINTER)
    }

    private fun printAll(messagesToPrint: List<Message>, printer: MessagePrinter): String =
        messagesToPrint
            .joinToString(System.lineSeparator()) { msg ->
                msg.printWith(printer, dateTimeProvider)
            }
            .trim()
}