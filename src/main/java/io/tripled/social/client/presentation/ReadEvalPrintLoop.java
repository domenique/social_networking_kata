package io.tripled.social.client.presentation;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ReadEvalPrintLoop {

  private static final String QUIT_COMMAND = "\\q";
  private final Input input;
  private final Output output;
  private final List<Command> commands;
  private boolean quitCommandReceived;

  public ReadEvalPrintLoop(Input input, Output output, List<Command> commands) {
    Objects.requireNonNull(input, "the Input cannot be null");
    Objects.requireNonNull(output, "the Output cannot be null");
    Objects.requireNonNull(commands, "The Commands cannot be null");
    this.input = input;
    this.output = output;
    this.commands = Collections.unmodifiableList(commands);
  }

  public boolean isStopped() {
    return quitCommandReceived;
  }

  public void start() {
    quitCommandReceived = false;
    while (!quitCommandReceived) {
      String readLine = input.read();
      if (isQuitCommand(readLine)) {
        quitCommandReceived = true;
        break;
      }
      executeCommands(readLine);
    }
  }

  private void executeCommands(String readLine) {
    commands.stream()
        .filter(c -> c.supports(readLine))
        .forEach(c -> c.execute(readLine, output));
  }

  private boolean isQuitCommand(String readLine) {
    return QUIT_COMMAND.equals(readLine);
  }
}