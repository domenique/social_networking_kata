package io.tripled.social.client.presentation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ReadEvalPrintLoopTest {

  private ReadEvalPrintLoop repl;
  private TestInput input;
  private TestOutput output;

  @BeforeEach
  void setUpReplWithEchoCommand() {
    input = new TestInput();
    output = new TestOutput();
    repl = new ReadEvalPrintLoop(input, output, null, null, null, null);
  }

  @Test
  void throwsExceptionWithNullInput() {
    assertThrows(NullPointerException.class, () -> {
      new ReadEvalPrintLoop(null, output, null, null, null, null);
    });
  }

  @Test
  void throwsExceptionWithNullOutput() {
    assertThrows(NullPointerException.class, () -> {
      new ReadEvalPrintLoop(new TestInput(), null, null, null, null, null);
    });
  }

  @Test
  void quits() {
    input.addInput("\\q");

    repl.run();

    assertThat(repl.isStopped(), is(true));
    output.assertIsEmpty();
  }

}
