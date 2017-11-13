package io.tripled.social.client.presentation;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ReadEvalPrintLoopTest {

  private ReadEvalPrintLoop repl;
  private TestInput input;
  private TestOutput output;

  @Before
  public void setUpReplWithEchoCommand() {
    input = new TestInput();
    output = new TestOutput();
    repl = new ReadEvalPrintLoop(input, output, null, null, null, null);
  }

  @Test(expected = NullPointerException.class)
  public void throwsExceptionWithNullInput() {
    new ReadEvalPrintLoop(null, output, null, null, null, null);
  }

  @Test(expected = NullPointerException.class)
  public void throwsExceptionWithNullOutput() {
    new ReadEvalPrintLoop(new TestInput(), null, null, null, null, null);
  }

  @Test
  public void quits() {
    input.addInput("\\q");

    repl.run();

    assertThat(repl.isStopped(), is(true));
    output.assertIsEmpty();
  }

}
