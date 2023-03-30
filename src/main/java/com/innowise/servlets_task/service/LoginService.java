package com.innowise.servlets_task.service;


import com.innowise.servlets_task.dto.AccountDTO;
import com.innowise.servlets_task.dto.LoginDTO;
import com.innowise.servlets_task.entity.Rank;
import com.innowise.servlets_task.utils.JWTSecurityUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

  public int checkUserAccessLevel() {
    return 0;
  }

  public String userLogin(HttpServletRequest servletRequest, HttpServletResponse servletResponse,
      LoginDTO userLogin){

    JWTSecurityUtils jwtSecurityUtils = JWTSecurityUtils.getInstance();
    String userToken = null;

    if(accountService.checkExistingUser(userLogin.getUserId(), userLogin.getPassword())){
      userLogin.setRank(accountService.getAccountByID(userLogin.getUserId()).getRank());
      userToken = jwtSecurityUtils.generateUserToken(userLogin);
    }
    return userToken;
  }

  public int checkAccessLevel(HttpSession httpSession) {

    Object accessLevel = httpSession.getAttribute("userAccessLevel");
    if (accessLevel == null || Integer.parseInt(accessLevel.toString()) < 1) {
      return 1;
    } else {
      return Integer.parseInt(accessLevel.toString());
    }
  }

  public Rank setNewAccessLevel(HttpSession httpSession, LoginDTO loginDTO) {

    AccountDTO userAccount = accountService.getAccountByID(loginDTO.getUserId());
    if (userAccount != null) {
      int userAccessLevel = accountService.checkUserRank(httpSession.getAttribute("userId"),
          httpSession.getAttribute("userPassword"));
      if (userAccessLevel > 0) {
        httpSession.setAttribute("userId", String.valueOf(loginDTO.getUserId()));
        httpSession.setAttribute("userPassword", loginDTO.getPassword());
        httpSession.setAttribute("userAccessLevel", String.valueOf(userAccessLevel));
        return userAccount.getRank();
      }
      return null;
    } else {
      return null;
    }
  }
}
