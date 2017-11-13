package io.tripled.social.client;

import io.tripled.social.client.application.UseCaseFactory;
import io.tripled.social.client.domain.DateTimeProvider;
import io.tripled.social.client.domain.SocialNetworkRepository;
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
import java.util.Optional;
import java.util.ServiceLoader;

public class SocialNetworkApplication {

    public static void main(String[] args) {
        ReadEvalPrintLoop repl = createRepl();

        new Thread(repl).start();
    }

    private static ReadEvalPrintLoop createRepl() {
        Input input = new ConsoleInput(new BufferedReader(new InputStreamReader(System.in)));
        Output output = new ConsoleOutput();

        final DateTimeProvider dateTimeProvider = loadSystemDateProvider();
        final SocialNetworkRepository socialNetworkRepository = loadSocialNetworkRepository();
        final UseCaseFactory useCaseFactory = new UseCaseFactory(socialNetworkRepository);
        return new ReadEvalPrintLoop(input, output,
                new PostController(useCaseFactory.createPostMessageUseCase(dateTimeProvider)),
                new ReadController(useCaseFactory.createReadMessagesUseCase()),
                new WallController(useCaseFactory.createReadWallUseCase()),
                new FollowController(useCaseFactory.createFollowUserUseCase()));
    }

    private static DateTimeProvider loadSystemDateProvider() {
        final ServiceLoader<DateTimeProvider> repositories = ServiceLoader.load(DateTimeProvider.class);
        final Optional<DateTimeProvider> first = repositories.findFirst();
        return first.orElseThrow(RuntimeException::new);
    }

    private static SocialNetworkRepository loadSocialNetworkRepository() {
        final ServiceLoader<SocialNetworkRepository> repositories = ServiceLoader.load(SocialNetworkRepository.class);
        final Optional<SocialNetworkRepository> first = repositories.findFirst();
        return first.orElseThrow(RuntimeException::new);
    }
}
