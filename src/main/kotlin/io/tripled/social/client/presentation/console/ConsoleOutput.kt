package io.tripled.social.client.presentation.console

import io.tripled.social.client.presentation.Output

class ConsoleOutput : Output {
    override fun print(message: String) {
        println(message)
    }
}
