package com.innowise.servlets_task.controller.commands.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.innowise.servlets_task.controller.commands.CommandClass;
import com.innowise.servlets_task.service.AccountService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ResourceNotFoundExecutableCommand extends CommandClass {

  private final String errorCode = "errorcode: 404";
  private final String errorMessage = "File or directory not found. The resource you are looking"
      + " for might have been removed, had its name changed, or is temporarily unavailable";


  public ResourceNotFoundExecutableCommand() {
    super(null);
  }

  @Override
  public void executeGet(HttpServletRequest servletRequest,
      HttpServletResponse servletResponse) {

    printResponseJSON(errorCode, errorMessage, servletResponse);
  }
  @Override
  public void executePost(HttpServletRequest servletRequest,
      HttpServletResponse servletResponse) {

    printResponseJSON(errorCode, errorMessage, servletResponse);
  }
  @Override
  public void executeUpdate(HttpServletRequest servletRequest,
      HttpServletResponse servletResponse) {

    printResponseJSON(errorCode, errorMessage, servletResponse);
  }
  @Override
  public void executeDelete(HttpServletRequest servletRequest,
      HttpServletResponse servletResponse) {

    printResponseJSON(errorCode, errorMessage, servletResponse);
  }

  @Override
  public void printResponseJSON(String responseKey, String responseValue,
      HttpServletResponse servletResponse) {

    servletResponse.setHeader("ERROR 404", "Not Found");

    try (PrintWriter printWriter = servletResponse.getWriter()) {

      servletResponse.setContentType("application/json");

      ObjectMapper objectMapper = new ObjectMapper();
      ObjectNode responseJson = objectMapper.createObjectNode();

      responseJson.put(responseKey, responseValue);
      printWriter.write(responseJson.toString());

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
