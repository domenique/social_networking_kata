package io.tripled.social.client.infrastructure.cli

interface Controller<T> {
    fun execute(request: T, output: Output)
}
