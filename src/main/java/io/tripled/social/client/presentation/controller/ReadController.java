package io.tripled.social.client.presentation.controller;

import io.tripled.social.client.application.ReadMessagesUseCase;
import io.tripled.social.client.presentation.Output;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadController extends AbstractController {

  private ReadMessagesUseCase readMessagesApplicationService;

  public ReadController(ReadMessagesUseCase service) {
    this.readMessagesApplicationService = service;
  }

  @Override
  protected Pattern getPattern() {
    return Pattern.compile("(\\w+)");
  }

  @Override
  public void execute(String inputLine, Output output) {
    Matcher matcher = getPattern()
        .matcher(inputLine.trim());

    if (matcher.matches()) {
      String userName = matcher.group(1).trim();

      output.print(readMessagesApplicationService.readMessage(userName));
    }
  }
}
