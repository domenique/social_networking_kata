package io.tripled.social.client.infrastructure.cli.request

import io.tripled.social.client.infrastructure.cli.Output

interface Visitable {
    fun accept(visitor: Visitor, output: Output)
}
