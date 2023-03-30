package com.innowise.servlets_task.controller.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.innowise.servlets_task.dto.DTO;
import com.innowise.servlets_task.service.AccountService;
import com.innowise.servlets_task.service.LoginService;
import com.mysql.cj.log.Log;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class CommandClass implements Command {

  protected AccountService accountService;
  protected LoginService loginService;
  private final String errorCode = "error code: 405";
  private String errorMessage = "";
  public String defaultResponseCode = "response: ";

  public CommandClass(AccountService accountService, LoginService loginService) {
    this.accountService = accountService;
    this.loginService = loginService;
  }

  @Override
  public void executeGet(HttpServletRequest servletRequest,
      HttpServletResponse servletResponse) {

    servletResponse.setHeader("ERROR 405", "Method not allowed");
    errorMessage +=
        "Method " + servletRequest.getMethod() + " is not allowed for the resource " +
            servletRequest.getPathInfo();
    printResponseJSON(errorCode, errorMessage, servletResponse);
  }

  @Override
  public void executePost(HttpServletRequest servletRequest, HttpServletResponse servletResponse) {

    servletResponse.setHeader("ERROR 405", "Method not allowed");
    errorMessage +=
        "Method " + servletRequest.getMethod() + " is not allowed for the resource " +
            servletRequest.getPathInfo();
    printResponseJSON(errorCode, errorMessage, servletResponse);
  }

  @Override
  public void executeUpdate(HttpServletRequest servletRequest,
      HttpServletResponse servletResponse) {

    servletResponse.setHeader("ERROR 405", "Method not allowed");
    errorMessage +=
        "Method " + servletRequest.getMethod() + " is not allowed for the resource " +
            servletRequest.getPathInfo();
    printResponseJSON(errorCode, errorMessage, servletResponse);
  }

  @Override
  public void executeDelete(HttpServletRequest servletRequest,
      HttpServletResponse servletResponse) {

    servletResponse.setHeader("ERROR 405", "Method not allowed");
    errorMessage +=
        "Method " + servletRequest.getMethod() + " is not allowed for the resource " +
            servletRequest.getPathInfo();
    printResponseJSON(errorCode, errorMessage, servletResponse);
  }

  @Override
  public void printResponseJSON(String responseKey, String responseValue,
      HttpServletResponse servletResponse) {
    try (PrintWriter printWriter = servletResponse.getWriter()) {

      ObjectMapper objectMapper = new ObjectMapper();
      ObjectNode responseJson = objectMapper.createObjectNode();

      responseJson.put(responseKey, responseValue);
      printWriter.write(responseJson.toString() + "\n");

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public DTO parseFromJson(HttpServletRequest servletRequest) {
    return null;
  }
}
