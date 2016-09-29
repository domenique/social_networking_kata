package io.tripled.social.client.presentation;

import io.tripled.social.client.presentation.controller.EchoController;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class ReadEvalPrintLoopTest {

  private ReadEvalPrintLoop repl;
  private TestInput input;
  private TestOutput output;
  private EchoController echoCommand;

  @Before
  public void setUpReplWithEchoCommand() {
    input = new TestInput();
    output = new TestOutput();
    echoCommand = new EchoController();
    repl = new ReadEvalPrintLoop(input, output, Collections.singletonList(echoCommand));
  }

  @Test(expected = NullPointerException.class)
  public void throwsExceptionWithNullInput() {
    new ReadEvalPrintLoop(null, output, Collections.emptyList());
  }

  @Test(expected = NullPointerException.class)
  public void throwsExceptionWithNullOutput() {
    new ReadEvalPrintLoop(new TestInput(), null, Collections.emptyList());
  }

  @Test(expected = NullPointerException.class)
  public void throwsExceptionWithNullCommands() {
    new ReadEvalPrintLoop(new TestInput(), new TestOutput(), null);
  }

  @Test
  public void quits() {
    input.addInput("\\q");

    repl.run();

    assertThat(repl.isStopped(), is(true));
    echoCommand.assertMessage(nullValue(String.class));
    output.assertIsEmpty();
  }

  @Test
  public void echoesInput() {
    input.addInput("hello", "\\q");

    repl.run();

    assertThat(repl.isStopped(), is(true));
    echoCommand.assertMessage(equalTo("hello"));
    output.assertContains(equalTo("hello"));
  }
}
