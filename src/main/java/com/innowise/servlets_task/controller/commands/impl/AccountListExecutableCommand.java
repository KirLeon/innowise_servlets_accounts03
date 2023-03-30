package com.innowise.servlets_task.controller.commands.impl;

import com.innowise.servlets_task.controller.commands.CommandClass;
import com.innowise.servlets_task.dto.AccountDTO;
import com.innowise.servlets_task.service.AccountService;
import com.innowise.servlets_task.service.LoginService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AccountListExecutableCommand extends CommandClass {

  public AccountListExecutableCommand(AccountService accountService, LoginService loginService) {
    super(accountService, loginService);
  }

  @Override
  public void executeGet(HttpServletRequest request, HttpServletResponse response) {

    List<AccountDTO> accountDTOList = accountService.getAllAccounts();
    String responseMessage = accountDTOList.toString();

    printResponseJSON(defaultResponseCode, responseMessage, response);
  }
}
