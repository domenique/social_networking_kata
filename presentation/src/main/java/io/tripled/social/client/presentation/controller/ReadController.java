package io.tripled.social.client.presentation.controller;

import io.tripled.social.client.application.ReadMessagesUseCase;
import io.tripled.social.client.presentation.Controller;
import io.tripled.social.client.presentation.Output;
import io.tripled.social.client.presentation.request.ReadCliRequest;

public class ReadController implements Controller<ReadCliRequest> {

  private ReadMessagesUseCase readMessagesApplicationService;

  public ReadController(ReadMessagesUseCase service) {
    this.readMessagesApplicationService = service;
  }

  @Override
  public void execute(ReadCliRequest request, Output output) {
    output.print(readMessagesApplicationService.readMessage(request.getUserName()));
  }
}
