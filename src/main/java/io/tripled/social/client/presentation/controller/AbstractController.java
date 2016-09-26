package io.tripled.social.client.presentation.controller;

import io.tripled.social.client.presentation.Controller;
import io.tripled.social.client.presentation.Output;

import java.util.regex.Pattern;

public abstract class AbstractController implements Controller {

  @Override
  public boolean supports(String readLine) {
    return readLine != null && getPattern().matcher(readLine.trim())
        .matches();
  }

  protected abstract Pattern getPattern();

  public abstract void execute(String inputLine, Output output);
}
