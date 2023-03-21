package com.innowise.servlets_task.controller.commands.impl;

import com.innowise.servlets_task.controller.commands.CommandClass;
import com.innowise.servlets_task.dto.DTO;
import com.innowise.servlets_task.service.AccountService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SessionExecutableCommand extends CommandClass {

  public SessionExecutableCommand(AccountService accountService) {
    super(accountService);
  }

}
