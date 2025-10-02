package io.tripled.social.client.infrastructure.cli.console

import io.tripled.social.client.infrastructure.cli.Input
import java.io.BufferedReader
import java.io.IOException

class ConsoleInput(private val bufferedReader: BufferedReader) : Input {
    override fun read(): String? {
        try {
            return bufferedReader.readLine()
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
    }
}
