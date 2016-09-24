package io.tripled.social.client;

import io.tripled.social.client.presentation.console.ConsoleInput;
import io.tripled.social.client.presentation.console.ConsoleOutput;
import io.tripled.social.client.presentation.Input;
import io.tripled.social.client.presentation.Output;
import io.tripled.social.client.presentation.ReadEvalPrintLoop;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;

public class SocialNetworkApplication {

  public static void main(String[] args) {
    ReadEvalPrintLoop repl = createRepl();

    repl.start();
  }

  private static ReadEvalPrintLoop createRepl() {
    Input input = new ConsoleInput(new BufferedReader(new InputStreamReader(System.in)));
    Output output = new ConsoleOutput();

    return new ReadEvalPrintLoop(input, output, Collections.emptyList());
  }
}
