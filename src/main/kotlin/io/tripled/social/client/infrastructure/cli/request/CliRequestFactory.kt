package io.tripled.social.client.infrastructure.cli.request

import io.tripled.social.client.infrastructure.cli.CliRequest
import java.util.regex.Matcher
import java.util.regex.Pattern

class CliRequestFactory {
    private val factories: List<RequestFactory> = listOf(
        PostCliRequestFactory(), ReadCliRequestFactory(),
        FollowCliRequestFactory(), WallCliRequestFactory()
    )

    fun create(input: String): CliRequest? {
        return factories
            .filter { it.supports(input) }
            .map { it.create(input) }
            .first()

    }

    internal interface RequestFactory {
        fun supports(input: String): Boolean

        fun create(input: String): CliRequest?
    }

    private inner class PostCliRequestFactory : RequestFactory {
        override fun create(input: String): CliRequest? {
            val matcher = getMatcher(input)
            if (matcher.matches()) {
                return PostCliRequest(matcher.group(1).trim { it <= ' ' }, matcher.group(2).trim { it <= ' ' })
            }
            return null
        }

        override fun supports(input: String): Boolean {
            return getMatcher(input).matches()
        }

        fun getMatcher(inputLine: String): Matcher {
            return Pattern.compile("(.+) -> (.+)").matcher(inputLine.trim { it <= ' ' })
        }
    }

    private inner class ReadCliRequestFactory : RequestFactory {
        override fun create(input: String): CliRequest? {
            val matcher = getMatcher(input)
            if (matcher.matches()) {
                return ReadCliRequest(matcher.group(1).trim { it <= ' ' })
            }
            return null
        }

        override fun supports(input: String): Boolean {
            return getMatcher(input).matches()
        }

        fun getMatcher(inputLine: String): Matcher {
            return Pattern.compile("(\\w+)").matcher(inputLine.trim { it <= ' ' })
        }
    }

    private inner class FollowCliRequestFactory : RequestFactory {
        override fun create(input: String): CliRequest? {
            val matcher = getMatcher(input)
            if (matcher.matches()) {
                return FollowCliRequest(matcher.group(1).trim { it <= ' ' }, matcher.group(2).trim { it <= ' ' })
            }
            return null
        }

        override fun supports(input: String): Boolean {
            return getMatcher(input).matches()
        }

        fun getMatcher(inputLine: String): Matcher {
            return Pattern.compile("(.+) follows (.+)").matcher(inputLine.trim { it <= ' ' })
        }
    }

    private inner class WallCliRequestFactory : RequestFactory {
        override fun create(input: String): CliRequest? {
            val matcher = getMatcher(input)
            if (matcher.matches()) {
                return WallCliRequest(matcher.group(1).trim { it <= ' ' })
            }
            return null
        }

        override fun supports(input: String): Boolean {
            return getMatcher(input).matches()
        }

        fun getMatcher(inputLine: String): Matcher {
            return Pattern.compile("(.+) wall").matcher(inputLine.trim { it <= ' ' })
        }
    }
}
