package com.innowise.servlets_task.controller.commands.impl;

import com.innowise.servlets_task.controller.commands.CommandClass;
import com.innowise.servlets_task.dto.AccountDTO;
import com.innowise.servlets_task.service.AccountService;
import com.innowise.servlets_task.service.LoginService;
import java.util.List;
import java.util.stream.Stream;
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

    int UAL = (int) request.getSession().getAttribute("UAL");
    Stream<AccountDTO> accessDataFilterStream = accountDTOList.stream();
    if (UAL < 2) {
      accessDataFilterStream.forEach(accountDTO -> {
        accountDTO.setSalary(0);
        accountDTO.setPassword("ACCESS LEVEL 3 REQUIRED");
      });
    } else if (UAL < 3) {
      accessDataFilterStream.forEach(
          accountDTO -> accountDTO.setPassword("ACCESS LEVEL 3 REQUIRED"));
    }

    printResponseJSON(defaultResponseCode, responseMessage, response);
  }
}
