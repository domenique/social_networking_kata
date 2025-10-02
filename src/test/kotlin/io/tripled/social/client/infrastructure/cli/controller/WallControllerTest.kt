package io.tripled.social.client.infrastructure.cli.controller

import io.tripled.social.client.application.usecase.spies.ReadWallSpy
import io.tripled.social.client.infrastructure.cli.TestOutput
import io.tripled.social.client.infrastructure.cli.request.WallCliRequest
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class WallControllerTest {
    private var wallController: WallController? = null
    private var service: ReadWallSpy? = null

    @BeforeEach
    fun setUp() {
        service = ReadWallSpy()
        wallController = WallController(service!!)
    }

    @Test
    fun executesCommand() {
        val request = WallCliRequest("Alice")

        val output = TestOutput()
        wallController!!.execute(request, output)

        MatcherAssert.assertThat<String?>(service!!.userName, Matchers.equalTo<String?>("Alice"))
        output.assertContains(Matchers.equalTo<String?>("Alice"))
    }
}
