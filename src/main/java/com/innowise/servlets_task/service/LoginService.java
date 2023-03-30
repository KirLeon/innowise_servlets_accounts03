package com.innowise.servlets_task.service;

import com.innowise.servlets_task.dto.LoginDTO;
import com.innowise.servlets_task.utils.JWTSecurityUtils;

public class LoginService {

  private final AccountService accountService;
  private static LoginService loginInstance;

  private LoginService() {
    accountService = AccountService.getServiceInstance();
  }

  public static LoginService getInstance() {
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

  public String userLogin(LoginDTO userLogin) {

    JWTSecurityUtils jwtSecurityUtils = JWTSecurityUtils.getInstance();
    String userToken = null;

    if (accountService.checkExistingUser(userLogin.getUserId(), userLogin.getPassword())) {
      userLogin.setRank(accountService.getAccountByID(userLogin.getUserId()).getRank());
      userToken = jwtSecurityUtils.generateUserToken(userLogin);
    }
    return userToken;
  }


}
