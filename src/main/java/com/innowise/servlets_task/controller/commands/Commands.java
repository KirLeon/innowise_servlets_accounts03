package com.innowise.servlets_task.controller.commands;

import com.innowise.servlets_task.controller.servlets.AddAccountServlet;
import com.innowise.servlets_task.controller.servlets.GetAccountInfoServlet;

public enum Commands {
  ADD_ACCOUNT("/account/add", new AddAccountServlet()),
  GET_ACCOUNT_BY_ID("/account", new GetAccountInfoServlet()),
  GET_ALL_ACCOUNTS("/accounts", new AddAccountServlet()),
  EDIT_ACCOUNT("/account/edit", new AddAccountServlet()),
  DELETE_ACCOUNT("/account/delete", new GetAccountInfoServlet()),
  SESSION_LOGIN("/session/login", new GetAccountInfoServlet()),
  SESSION_LOGOUT("/session/logout", new AddAccountServlet());

  private final String urlPath;

  private final ServletExecutable servlet;

  public String getUrlPath() {
    return urlPath;
  }

  public ServletExecutable getServlet() {
    return servlet;
  }

  Commands(String urlPath, ServletExecutable servlet) {
    this.urlPath = urlPath;
    this.servlet = servlet;
  }
}
