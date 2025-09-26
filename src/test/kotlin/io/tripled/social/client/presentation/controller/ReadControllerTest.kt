package io.tripled.social.client.presentation.controller

import io.tripled.social.client.application.SpiedReadMessagesUseCase
import io.tripled.social.client.presentation.TestOutput
import io.tripled.social.client.presentation.request.ReadCliRequest
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class ReadControllerTest {
    private var readController: ReadController? = null
    private var service: SpiedReadMessagesUseCase? = null

    @BeforeEach
    fun setUp() {
        service = SpiedReadMessagesUseCase()
        readController = ReadController(service!!)
    }

    @Test
    fun executesCommand() {
        val request = ReadCliRequest("Alice")

        readController!!.execute(request, TestOutput())

        MatcherAssert.assertThat<String?>(service!!.userName, Matchers.equalTo<String?>("Alice"))
    }
}