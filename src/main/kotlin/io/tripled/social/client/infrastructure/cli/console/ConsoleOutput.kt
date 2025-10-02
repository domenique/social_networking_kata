package io.tripled.social.client.infrastructure.cli.console

import io.tripled.social.client.infrastructure.cli.Output

class ConsoleOutput : Output {
    override fun print(message: String) {
        println(message)
    }
}
