package io.tripled.social.client.infrastructure.cli

interface Input {
    fun read(): String?
}
