package io.tripled.social.client.presentation.request

import io.tripled.social.client.presentation.Output

interface Visitable {
    fun accept(visitor: Visitor, output: Output)
}
