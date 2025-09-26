package io.tripled.social.client.presentation

interface Controller<T> {
    fun execute(request: T, output: Output)
}
