package io.tripled.social.client

import io.ktor.server.application.Application
import io.ktor.server.netty.EngineMain
import io.tripled.social.client.infrastructure.rest.configureRouting
import io.tripled.social.client.infrastructure.rest.configureSerialization

fun main(args: Array<String>) {
    EngineMain.main(args)
}

fun Application.module() {
    configureSerialization()
    configureRouting()
}
