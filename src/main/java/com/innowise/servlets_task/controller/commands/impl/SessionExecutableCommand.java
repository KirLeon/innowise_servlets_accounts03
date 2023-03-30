package com.innowise.servlets_task.controller.commands.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.innowise.servlets_task.controller.commands.CommandClass;
import com.innowise.servlets_task.dto.LoginDTO;
import com.innowise.servlets_task.service.AccountService;
import com.innowise.servlets_task.service.LoginService;
import com.innowise.servlets_task.utils.CookieUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionExecutableCommand extends CommandClass {

  public SessionExecutableCommand(AccountService accountService, LoginService loginService) {
    super(accountService, loginService);
  }

  @Override
  public void executePost(HttpServletRequest servletRequest, HttpServletResponse servletResponse) {

    Cookie[] cookie = servletRequest.getCookies();


    HttpSession session = servletRequest.getSession();

    LoginDTO loginDTO = parseFromJson(servletRequest);

    loginDTO.setRank(loginService.setNewAccessLevel(session, loginDTO));
    String responseMessage;

    if (loginDTO.getRank() != null) {
      responseMessage = "You have successfully logged in " +
          "account. Your access level is: " + (loginDTO.getRank().ordinal() + 1) + ".";

      printResponseJSON(defaultResponseCode, responseMessage, servletResponse);
    } else {
      String errorCode = "errorcode: 400";
      responseMessage = "Cannot identify the account. Please, check your input and try again.";
      printResponseJSON(errorCode, responseMessage, servletResponse);
    }
  }

  @Override
  public void executeDelete(HttpServletRequest servletRequest,
      HttpServletResponse servletResponse) {

    HttpSession session = servletRequest.getSession();
    session.setAttribute("userAccessLevel", null);
    session.setAttribute("userId", null);
    session.setAttribute("userPassword", null);

    String responseMessage = "You successfully logged out from system";
    printResponseJSON(defaultResponseCode, responseMessage, servletResponse);
  }

  @Override
  public void executeUpdate(HttpServletRequest servletRequest,
      HttpServletResponse servletResponse) {
    executePost(servletRequest, servletResponse);
  }

  @Override
  public LoginDTO parseFromJson(HttpServletRequest request) {

    LoginDTO loginDTO = null;

    try (BufferedReader readerJSON = request.getReader()) {

      StringBuilder bufferJSON = new StringBuilder();
      String line;
      while ((line = readerJSON.readLine()) != null) {
        bufferJSON.append(line);
      }

      ObjectMapper objectMapper = new ObjectMapper();
      loginDTO = objectMapper.readValue(bufferJSON.toString(), LoginDTO.class);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return loginDTO;
  }
}
