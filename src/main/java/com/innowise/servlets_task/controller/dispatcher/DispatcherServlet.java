package com.innowise.servlets_task.controller.dispatcher;

import com.innowise.servlets_task.controller.commands.Command;
import com.innowise.servlets_task.controller.commands.CommandURLs;
import com.innowise.servlets_task.controller.commands.impl.AccountExecutableCommand;
import com.innowise.servlets_task.controller.commands.impl.AccountListExecutableCommand;
import com.innowise.servlets_task.controller.commands.impl.LoginExecutableCommand;
import com.innowise.servlets_task.controller.commands.impl.ResourceNotFoundExecutableCommand;
import com.innowise.servlets_task.service.AccountService;
import com.innowise.servlets_task.service.LoginService;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "helloServlet", value = "/*")
public class DispatcherServlet extends HttpServlet {

  private Map<CommandURLs, Command> commandMap;

  public void init() {
    commandMap = new HashMap<>();
    AccountService accountService = AccountService.getServiceInstance();
    LoginService loginService = LoginService.getInstance();

    commandMap.put(CommandURLs.ACCOUNT,
        new AccountExecutableCommand(accountService, loginService));

    commandMap.put(CommandURLs.ACCOUNTS,
        new AccountListExecutableCommand(accountService, loginService));

    commandMap.put(CommandURLs.LOGIN,
        new LoginExecutableCommand(accountService, loginService));

    commandMap.put(CommandURLs.ERROR, new ResourceNotFoundExecutableCommand());
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) {

    Command command = getExecutableCommand(request);
    command.executeGet(request, response);
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) {

    Command command = getExecutableCommand(request);
    command.executePost(request, response);
  }

  @Override
  public void doPut(HttpServletRequest request, HttpServletResponse response) {

    Command command = getExecutableCommand(request);
    command.executeUpdate(request, response);
  }

  @Override
  public void doDelete(HttpServletRequest request, HttpServletResponse response) {

    Command command = getExecutableCommand(request);
    command.executeDelete(request, response);
  }


  public void destroy() {
  }

  public Command getExecutableCommand(HttpServletRequest request) {

    String path = request.getPathInfo();
    return commandMap.get(
        commandMap.keySet().stream()
            .filter(commandKey -> commandKey.getUrlPath().equals(path))
            .findFirst()
            .orElse(CommandURLs.ERROR)
    );
  }
}