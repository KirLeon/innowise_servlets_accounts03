package com.innowise.servlets_task.controller.commands.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.innowise.servlets_task.controller.commands.CommandClass;
import com.innowise.servlets_task.dto.AccountDTO;
import com.innowise.servlets_task.service.AccountService;
import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AccountExecutableCommand extends CommandClass {

  public AccountExecutableCommand(AccountService accountService) {
    super(accountService);
  }

  @Override
  public void executeGet(HttpServletRequest request, HttpServletResponse response) {

    AccountDTO accountDTO = parseFromJson(request);

    int accountId = accountDTO.getId();
    accountDTO = accountService.getAccountByID(accountId);
    String responseText = accountDTO != null ? "The account is: " + accountDTO
        : "We cannot find the account with id: " + accountId;

    printResponseJSON(defaultResponseCode, responseText, response);
  }

  @Override
  public void executePost(HttpServletRequest request, HttpServletResponse response) {

    AccountDTO accountDTO = parseFromJson(request);

    accountDTO = accountService.createAccount(accountDTO);
    String responseText = accountDTO != null ? "Account has been successfully created"
        : "Something went wrong. Please, check your input and try again";

    printResponseJSON(defaultResponseCode, responseText, response);
  }
  @Override
  public void executeUpdate(HttpServletRequest request, HttpServletResponse response) {

    AccountDTO accountDTO = parseFromJson(request);

    accountDTO = accountService.editAccount(accountDTO);
    String responseText = accountDTO != null ? "Account has been successfully updated"
        : "Something went wrong. Please, check your input and try again";

    printResponseJSON(defaultResponseCode, responseText, response);
  }
  @Override
  public void executeDelete(HttpServletRequest request, HttpServletResponse response) {

    AccountDTO accountDTO = parseFromJson(request);

    int accountId = accountDTO.getId();
    accountDTO = accountService.deleteAccount(accountId);
    String responseText = accountDTO != null ? "Account has been successfully deleted"
        : "Something went wrong. Please, check your input and try again";

    printResponseJSON(defaultResponseCode, responseText, response);
  }
  @Override
  public AccountDTO parseFromJson(HttpServletRequest request) {

    AccountDTO accountDTO = null;

    try (BufferedReader readerJSON = request.getReader()) {

      StringBuilder bufferJSON = new StringBuilder();
      String line;
      while ((line = readerJSON.readLine()) != null) {
        bufferJSON.append(line);
      }

      ObjectMapper objectMapper = new ObjectMapper();
      accountDTO = objectMapper.readValue(bufferJSON.toString(), AccountDTO.class);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return accountDTO;
  }


}
