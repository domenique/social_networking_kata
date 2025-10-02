package io.tripled.social.client.infrastructure.cli.controller

import io.tripled.social.client.application.usecase.spies.FollowUserSpy
import io.tripled.social.client.infrastructure.cli.TestOutput
import io.tripled.social.client.infrastructure.cli.request.FollowCliRequest
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test

internal class FollowControllerTest {
    private val service: FollowUserSpy = FollowUserSpy()
    private val followController: FollowController = FollowController(service)

    @Test
    fun executesCommand() {
        val request = FollowCliRequest("Charlie", "Alice")

        followController!!.execute(request, TestOutput())

        MatcherAssert.assertThat<String?>(service!!.userName, Matchers.equalTo<String?>("Charlie"))
        MatcherAssert.assertThat<String?>(service!!.userNameToFollow, Matchers.equalTo<String?>("Alice"))
    }
}
