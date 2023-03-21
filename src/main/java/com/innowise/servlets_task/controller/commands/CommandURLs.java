package com.innowise.servlets_task.controller.commands;

public enum CommandURLs {
  ACCOUNT("/account"), ACCOUNTS("accounts"),
  SESSION("/session"), ERROR("/404");

  private final String urlPath;

  CommandURLs(String urlPath) {
    this.urlPath = urlPath;
  }

  public String getUrlPath() {
    return urlPath;
  }
}
