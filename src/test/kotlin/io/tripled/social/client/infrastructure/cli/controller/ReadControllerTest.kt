package io.tripled.social.client.infrastructure.cli.controller

import io.tripled.social.client.application.usecase.spies.ReadMessagesSpy
import io.tripled.social.client.infrastructure.cli.TestOutput
import io.tripled.social.client.infrastructure.cli.request.ReadCliRequest
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class ReadControllerTest {
    private var readController: ReadController? = null
    private var service: ReadMessagesSpy? = null

    @BeforeEach
    fun setUp() {
        service = ReadMessagesSpy()
        readController = ReadController(service!!)
    }

    @Test
    fun executesCommand() {
        val request = ReadCliRequest("Alice")

        readController!!.execute(request, TestOutput())

        MatcherAssert.assertThat<String?>(service!!.userName, Matchers.equalTo<String?>("Alice"))
    }
}