package com.innowise.servlets_task.controller.commands.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.innowise.servlets_task.controller.commands.CommandClass;
import com.innowise.servlets_task.dto.LoginDTO;
import com.innowise.servlets_task.service.AccountService;
import com.innowise.servlets_task.service.LoginService;
import com.innowise.servlets_task.utils.JWTSecurityUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginExecutableCommand extends CommandClass {

  public LoginExecutableCommand(AccountService accountService, LoginService loginService) {
    super(accountService, loginService);
  }

  @Override
  public void executePost(HttpServletRequest servletRequest, HttpServletResponse servletResponse) {

    LoginDTO userLogin = parseFromJson(servletRequest);
    String userToken = loginService.userLogin(servletRequest, servletResponse, userLogin);

    servletResponse.setContentType("application/json");
    String responseMessage;

    if(userToken != null){
      servletResponse.setHeader("Authorization", "Bearer " + userToken);
      responseMessage = "You've successfully logged in system. Here's your token " + userToken;
      printResponseJSON(defaultResponseCode, responseMessage, servletResponse);
    }
    else{
      servletResponse.setHeader("ERROR 401", "User login/password is incorrect");
      responseMessage = "Cannot identify user account";
      printResponseJSON("401", responseMessage, servletResponse);
    }
  }

  @Override
  public void executeDelete(HttpServletRequest servletRequest,
      HttpServletResponse servletResponse) {

    JWTSecurityUtils jwtSecurityUtils = JWTSecurityUtils.getInstance();
    servletRequest.getHeader("Authorization");
    servletResponse.setHeader("Authorization", null);

    servletResponse.setContentType("application/json");
    try {
      PrintWriter out = servletResponse.getWriter();
      out.write("You've successfully logged out");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
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
