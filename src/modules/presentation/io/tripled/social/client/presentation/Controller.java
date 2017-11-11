package io.tripled.social.client.presentation;

public interface Controller<T> {

  void execute(T request, Output output);
}
