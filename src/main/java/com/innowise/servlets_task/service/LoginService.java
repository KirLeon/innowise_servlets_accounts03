package com.innowise.servlets_task.service;


import com.innowise.servlets_task.dto.AccountDTO;
import com.innowise.servlets_task.dto.LoginDTO;

public class LoginService {

  private final AccountService accountService;
  private static LoginService loginInstance;

  private LoginService() {
    accountService = AccountService.getServiceInstance();
  }

  public LoginService getInstance() {
    LoginService instance = loginInstance;
    if (instance == null) {
      synchronized (LoginService.class) {
        instance = loginInstance;
        if (instance == null) {
          loginInstance = new LoginService();
          instance = loginInstance;
        }
      }
    }
    return instance;
  }

  public LoginDTO login(LoginDTO loginDTO) {
    int userId = loginDTO.getUserId();
    String userPassword = loginDTO.getPassword();
    AccountDTO userAccount = accountService.getAccountByID(userId);

    if (userAccount != null && accountService.checkUserPassword(userId, userPassword)) {
      loginDTO.setRank(userAccount.getRank());
      return loginDTO;
    }

    return null;
  }
}
