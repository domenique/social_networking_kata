package io.tripled.social.client.presentation.controller

import io.tripled.social.client.application.SpiedFollowUserUseCase
import io.tripled.social.client.presentation.TestOutput
import io.tripled.social.client.presentation.request.FollowCliRequest
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test

internal class FollowControllerTest {
    private val service: SpiedFollowUserUseCase = SpiedFollowUserUseCase()
    private val followController: FollowController = FollowController(service)

    @Test
    fun executesCommand() {
        val request = FollowCliRequest("Charlie", "Alice")

        followController!!.execute(request, TestOutput())

        MatcherAssert.assertThat<String?>(service!!.userName, Matchers.equalTo<String?>("Charlie"))
        MatcherAssert.assertThat<String?>(service!!.userNameToFollow, Matchers.equalTo<String?>("Alice"))
    }
}
