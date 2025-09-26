package io.tripled.social.client.presentation

import org.hamcrest.Matcher
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import java.util.*

class TestOutput : Output {
    private val outputs: LinkedList<String> = LinkedList<String>()

    override fun print(message: String) {
        this.outputs.add(message)
    }

    fun assertContains(vararg message: Matcher<String>) {
        MatcherAssert.assertThat(this.outputs, Matchers.hasItems(*message))
    }

    fun assertIsEmpty() {
        MatcherAssert.assertThat(this.outputs, Matchers.empty())
    }
}
