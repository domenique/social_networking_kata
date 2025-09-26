package io.tripled.social.client.acceptance

import io.tripled.social.client.presentation.Input
import java.util.concurrent.BlockingDeque
import java.util.concurrent.LinkedBlockingDeque

internal class CucumberInput : Input {
    private val inputs: BlockingDeque<String> = LinkedBlockingDeque()

    fun sendInput(input: String) {
        this.inputs.offer(input)
    }

    override fun read(): String? {
        return inputs.poll()
    }
}
