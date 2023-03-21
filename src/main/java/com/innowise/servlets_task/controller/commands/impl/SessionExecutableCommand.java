package com.innowise.servlets_task.controller.commands.impl;

import com.innowise.servlets_task.controller.commands.CommandClass;
import com.innowise.servlets_task.service.AccountService;

public class SessionExecutableCommand extends CommandClass {

  public SessionExecutableCommand(AccountService accountService) {
    super(accountService);
  }

}
