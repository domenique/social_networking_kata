package io.tripled.social.client.presentation;

import io.tripled.social.client.presentation.controller.FollowController;
import io.tripled.social.client.presentation.controller.PostController;
import io.tripled.social.client.presentation.controller.ReadController;
import io.tripled.social.client.presentation.controller.WallController;
import io.tripled.social.client.presentation.request.CliRequestFactory;
import io.tripled.social.client.presentation.request.RequestDispatcher;

import java.util.Objects;

public class ReadEvalPrintLoop implements Runnable {

    private static final String QUIT_COMMAND = "\\q";
    private final Input input;
    private final Output output;
    private final CliRequestFactory requestFactory;
    private final RequestDispatcher dispatcher;
    private boolean quitCommandReceived;

    public ReadEvalPrintLoop(Input input,
                             Output output,
                             PostController postController,
                             ReadController readController,
                             WallController wallController,
                             FollowController followController) {
        Objects.requireNonNull(input, "the Input cannot be null");
        Objects.requireNonNull(output, "the Output cannot be null");
        this.input = input;
        this.output = output;
        this.requestFactory = new CliRequestFactory();
        this.dispatcher = new RequestDispatcher(postController, readController, wallController, followController);
    }

    public boolean isStopped() {
        return quitCommandReceived;
    }

    @Override
    public void run() {
        output.print("Started ...");
        quitCommandReceived = false;
        while (!quitCommandReceived) {
            String readLine = input.read();
            if (isQuitCommand(readLine)) {
                quitCommandReceived = true;
                break;
            }
            executeCommands(readLine);
        }
        output.print("Finished");
    }

    private void executeCommands(String readLine) {
        output.print("read command [" + readLine + "]");
        requestFactory.create(readLine)
                      .ifPresent(request -> request.accept(dispatcher, output));
    }

    private boolean isQuitCommand(String readLine) {
        return QUIT_COMMAND.equals(readLine);
    }
}
