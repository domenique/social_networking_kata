package io.tripled.social.client.presentation.controller

import io.tripled.social.client.application.SpiedPostMessageUseCase
import io.tripled.social.client.presentation.TestOutput
import io.tripled.social.client.presentation.request.PostCliRequest
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class PostControllerTest {
    private var postController: PostController? = null
    private var service: SpiedPostMessageUseCase? = null

    @BeforeEach
    fun setUp() {
        service = SpiedPostMessageUseCase()
        postController = PostController(service!!)
    }

    @Test
    fun executesPostCommand() {
        val request = PostCliRequest("Alice", "Hi There!")


        postController!!.execute(request, TestOutput())

        MatcherAssert.assertThat<String?>(service!!.userName, Matchers.equalTo<String?>("Alice"))
        MatcherAssert.assertThat<String?>(service!!.message, Matchers.equalTo<String?>("Hi There!"))
    }
}