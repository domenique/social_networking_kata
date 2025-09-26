package io.tripled.social.client.presentation

import java.util.*

internal class TestInput : Input {
    private val inputs: Deque<String?>

    init {
        inputs = LinkedList<String?>()
    }

    fun addInput(vararg inputs: String?) {
        this.inputs.addAll(Arrays.asList<String?>(*inputs))
    }

    override fun read(): String? {
        return inputs.poll()
    }
}
