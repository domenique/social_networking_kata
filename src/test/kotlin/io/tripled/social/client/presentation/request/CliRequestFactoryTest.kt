package io.tripled.social.client.presentation.request

import io.tripled.social.client.presentation.CliRequest
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class CliRequestFactoryTest {
    private val factory: CliRequestFactory = CliRequestFactory()

    @Test
    @Throws(Exception::class)
    fun returnsPostCommand() {
        val input = "Alice -> Hi there!"

        val request: CliRequest? = factory.create(input)

        MatcherAssert.assertThat<CliRequest?>(request, Matchers.instanceOf<CliRequest?>(PostCliRequest::class.java))
    }

    @Test
    @Throws(Exception::class)
    fun returnsPostCommandWithTrimmedValues() {
        val input = "  Alice   ->   Hi There!  "

        val request: CliRequest? = factory.create(input)

        MatcherAssert.assertThat<CliRequest?>(request, Matchers.instanceOf<CliRequest?>(PostCliRequest::class.java))
        MatcherAssert.assertThat<CliRequest?>(
            request,
            Matchers.equalTo<CliRequest?>(PostCliRequest("Alice", "Hi There!"))
        )
    }

    @Test
    @Throws(Exception::class)
    fun returnsReadCommand() {
        val input = "Alice"
        val message = "  Alice  "

        val request: CliRequest? = factory.create(input)

        MatcherAssert.assertThat<CliRequest?>(request, Matchers.instanceOf<CliRequest?>(ReadCliRequest::class.java))
    }

    @Test
    @Throws(Exception::class)
    fun returnsReadCommandWithTrimmedValues() {
        val input = "  Alice  "

        val request: CliRequest? = factory.create(input)

        MatcherAssert.assertThat<CliRequest?>(request, Matchers.instanceOf<CliRequest?>(ReadCliRequest::class.java))
        MatcherAssert.assertThat<CliRequest?>(request, Matchers.equalTo<CliRequest?>(ReadCliRequest("Alice")))
    }

    @Test
    @Throws(Exception::class)
    fun returnsFollowCommand() {
        val input = "Alice follows Ben"

        val cliRequest: CliRequest? = factory.create(input)

        MatcherAssert.assertThat<CliRequest?>(
            cliRequest,
            Matchers.instanceOf<CliRequest?>(FollowCliRequest::class.java)
        )
    }

    @Test
    @Throws(Exception::class)
    fun retunsWallCommand() {
        val input = "Ben wall"

        val cliRequest: CliRequest? = factory.create(input)

        MatcherAssert.assertThat<CliRequest?>(cliRequest, Matchers.instanceOf<CliRequest?>(WallCliRequest::class.java))
    }
}