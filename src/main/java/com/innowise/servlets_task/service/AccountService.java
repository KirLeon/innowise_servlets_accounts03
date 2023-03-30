package com.innowise.servlets_task.service;

import com.innowise.servlets_task.dao.AccountDAO;
import com.innowise.servlets_task.dto.AccountDTO;
import com.innowise.servlets_task.entity.Account;
import com.innowise.servlets_task.mapper.AccountMapper;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;

public class AccountService {

  private static volatile AccountService accountService;

  private static AccountDAO accountDAO;

  private static String salt;

  private AccountService() {
    accountDAO = AccountDAO.getInstance();
    salt = BCrypt.gensalt(12);
  }

  public static AccountService getServiceInstance() {
    AccountService instance = accountService;
    if (instance == null) {
      synchronized (AccountService.class) {
        instance = accountService;
        if (instance == null) {
          accountService = new AccountService();
          instance = accountService;
        }
      }
    }

    return instance;
  }

  public AccountDTO getAccountByID(int id) {
    Account account = accountDAO.selectAccount(id);

    if (account != null) {
      //logging
      return AccountMapper.mapper.entityToDto(account);
    }

    //logging
    return null;
  }

  public List<AccountDTO> getAllAccounts() {
    List<Account> accounts = accountDAO.selectAllEmployees();

    //logging
    return accounts.stream().
        map(AccountMapper.mapper::entityToDto).toList();
  }

  public AccountDTO createAccount(AccountDTO createRequest) {

    String hashedPassword = hashPassword(createRequest.getPassword());
    createRequest.setPassword(hashedPassword);
    System.out.println(hashedPassword);

    Account accountToCreate = accountDAO.createNewAccount
        (AccountMapper.mapper.dtoToEntity(createRequest));

    if (accountToCreate != null) {
      //logging
      return AccountMapper.mapper.entityToDto(accountToCreate);
    }

    //logging
    return null;
  }

  public AccountDTO editAccount(AccountDTO updateRequest) {
    Account accountToUpdate = accountDAO.selectAccount(updateRequest.getId());

    if (accountToUpdate != null) {
      //logging
      accountToUpdate = accountDAO.updateAccount(AccountMapper.mapper.dtoToEntity(updateRequest));
      return AccountMapper.mapper.entityToDto(accountToUpdate);
    }

    //logging
    return null;
  }

  public AccountDTO deleteAccount(int id) {
    Account accountToDelete = accountDAO.deleteEmployee(id);

    if (accountToDelete != null) {
      //logging
      return AccountMapper.mapper.entityToDto(accountToDelete);
    }

    //logging
    return null;
  }

  public String hashPassword(String password) {
    return BCrypt.hashpw(password, salt);
  }

  public boolean checkExistingUser(int id, String checkPassword) {
    AccountDTO userAccount = getAccountByID(id);
    if (userAccount != null) {
      String actualPassword = getAccountByID(id).getPassword();
      if(BCrypt.checkpw(checkPassword, actualPassword))
      return BCrypt.checkpw(checkPassword, actualPassword);
    }
    return false;
  }

//  public Account login(int id, String password) {
//    Account account = accountDAO.selectAccount(id);
//    if (account != null) {
//      return checkUserPassword(id, password) ? account : null;
//    }
//    return null;
//  }

  public int checkUserRank(Object userId, Object password) {

    if (userId == null || password == null ||
        userId.toString().isEmpty() || password.toString().isEmpty()) {
      return 0;
    }

    int id = Integer.parseInt(userId.toString());
    AccountDTO userAccount = getAccountByID(id);
    return checkExistingUser(id, password.toString()) ? userAccount.getRank().ordinal() + 1 : 0;
  }


}
