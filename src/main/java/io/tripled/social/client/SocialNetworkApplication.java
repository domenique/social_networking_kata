package io.tripled.social.client;

import io.tripled.social.client.application.DefaultFollowUserUseCase;
import io.tripled.social.client.application.DefaultPostMessageUseCase;
import io.tripled.social.client.application.DefaultReadMessagesUseCase;
import io.tripled.social.client.application.DefaultReadWallUseCase;
import io.tripled.social.client.domain.DateTimeProvider;
import io.tripled.social.client.infrastructure.InMemorySocialNetworkRepository;
import io.tripled.social.client.infrastructure.SystemDateTimeProvider;
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

    new Thread(repl).start();
  }

  private static ReadEvalPrintLoop createRepl() {
    Input input = new ConsoleInput(new BufferedReader(new InputStreamReader(System.in)));
    Output output = new ConsoleOutput();

    DateTimeProvider dateTimeProvider = new SystemDateTimeProvider();
    InMemorySocialNetworkRepository socialNetworkRepository = new InMemorySocialNetworkRepository(dateTimeProvider);

    return new ReadEvalPrintLoop(input, output, Arrays.asList(
        new PostController(new DefaultPostMessageUseCase(socialNetworkRepository, dateTimeProvider)),
        new ReadController(new DefaultReadMessagesUseCase(socialNetworkRepository)),
        new FollowController(new DefaultFollowUserUseCase(socialNetworkRepository)),
        new WallController(new DefaultReadWallUseCase(socialNetworkRepository))
    ));
  }
}
