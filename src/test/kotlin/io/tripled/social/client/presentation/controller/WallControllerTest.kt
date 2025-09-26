package io.tripled.social.client.presentation.controller

import io.tripled.social.client.application.SpiedReadWallUseCase
import io.tripled.social.client.presentation.TestOutput
import io.tripled.social.client.presentation.request.WallCliRequest
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class WallControllerTest {
    private var wallController: WallController? = null
    private var service: SpiedReadWallUseCase? = null

    @BeforeEach
    fun setUp() {
        service = SpiedReadWallUseCase()
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
