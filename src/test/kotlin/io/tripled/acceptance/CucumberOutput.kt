package io.tripled.acceptance

import io.tripled.social.client.infrastructure.cli.Output
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import java.util.concurrent.BlockingDeque
import java.util.concurrent.LinkedBlockingDeque

internal class CucumberOutput : Output {
    private val outputs: BlockingDeque<String> = LinkedBlockingDeque()

    override fun print(message: String) {
        val split = message.split("\\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        for (part in split) {
            this.outputs.offer(part)
        }
    }

    fun assertContains(vararg lines: String?) {
        while (outputs.size < lines.size) {
            try {
                Thread.sleep(5)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }

        for (line in lines) {
            val resultLine = outputs.poll()
            MatcherAssert.assertThat<String?>(resultLine, Matchers.equalTo<String?>(line))
        }
    }
}
