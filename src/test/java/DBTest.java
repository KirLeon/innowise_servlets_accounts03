import com.innowise.servlets_task.dao.AccountDAO;
import com.innowise.servlets_task.dto.AccountDTO;
import com.innowise.servlets_task.entity.Account;
import com.innowise.servlets_task.entity.Departments;
import com.innowise.servlets_task.entity.Rank;
import com.innowise.servlets_task.mapper.AccountMapper;
import com.innowise.servlets_task.service.AccountService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DBTest {

  @Test
  public void selectTest(){

    int id = 6;
    String first_name = "Kir";
    String last_name = "Leon";
    String password = "123";
    double salary = 1200;
    Departments department = Departments.IT;
    Rank rank = Rank.EMPLOYEE;

    AccountService accountService = AccountService.getServiceInstance();
    AccountDAO accountDAO = AccountDAO.getInstance();

    AccountDTO requestDTO = AccountDTO.builder().id(id).firstName(first_name).lastName(last_name)
        .password(password).salary(salary).department(department).rank(rank).build();
    Account expectedAccount = AccountMapper.mapper.dtoToEntity(requestDTO);

    Account actualAccount = accountDAO.createNewAccount(expectedAccount);

    Assertions.assertEquals(expectedAccount, actualAccount);
    AccountDTO actualDTO = accountService.createAccount(requestDTO);

    actualAccount.setPassword(accountService.hashPassword(actualAccount.getPassword()));
    Assertions.assertEquals(AccountMapper.mapper.entityToDto(actualAccount), actualDTO);
  }
}
