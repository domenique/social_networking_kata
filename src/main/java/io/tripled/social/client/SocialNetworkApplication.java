package io.tripled.social.client;

import io.tripled.social.client.application.DefaultFollowUserUseCase;
import io.tripled.social.client.application.DefaultPostMessageUseCase;
import io.tripled.social.client.application.DefaultReadMessagesUseCase;
import io.tripled.social.client.application.DefaultReadWallUseCase;
import io.tripled.social.client.presentation.Input;
import io.tripled.social.client.presentation.Output;
import io.tripled.social.client.presentation.ReadEvalPrintLoop;
import io.tripled.social.client.presentation.console.ConsoleInput;
import io.tripled.social.client.presentation.console.ConsoleOutput;
import io.tripled.social.client.presentation.controller.FollowController;
import io.tripled.social.client.presentation.controller.PostController;
import io.tripled.social.client.presentation.controller.ReadController;
import io.tripled.social.client.presentation.controller.WallController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SocialNetworkApplication {

  public static void main(String[] args) {
    ReadEvalPrintLoop repl = createRepl();

    repl.start();
  }

  private static ReadEvalPrintLoop createRepl() {
    Input input = new ConsoleInput(new BufferedReader(new InputStreamReader(System.in)));
    Output output = new ConsoleOutput();

    return new ReadEvalPrintLoop(input, output, Arrays.asList(
        new PostController(new DefaultPostMessageUseCase()),
        new ReadController(new DefaultReadMessagesUseCase()),
        new FollowController(new DefaultFollowUserUseCase()),
        new WallController(new DefaultReadWallUseCase())
    ));
  }
}
