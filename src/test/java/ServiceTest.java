import com.innowise.servlets_task.service.AccountService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;

public class ServiceTest {

  @Test
  public void pwdTest(){
    AccountService accountService = AccountService.getServiceInstance();

    String pwd1 = "bv72bfyuiwev";
    String pwd2 = "bv72bfyuiwe" + "v";
    String pwd3 = "ufui*3";
    String pwd11 = accountService.hashPassword(pwd1);
    String pwd21 = accountService.hashPassword(pwd2);
    String pwd23 = accountService.hashPassword(pwd3);

    Assertions.assertEquals(pwd1, pwd2);
    Assertions.assertNotEquals(pwd1, pwd21);
    Assertions.assertNotEquals(pwd3, pwd2);

    Assertions.assertTrue(BCrypt.checkpw(pwd1, pwd11));
    Assertions.assertTrue(BCrypt.checkpw(pwd2, pwd21));
    Assertions.assertTrue(BCrypt.checkpw(pwd3, pwd23));

    Assertions.assertFalse(BCrypt.checkpw(pwd3, pwd11));
    Assertions.assertFalse(BCrypt.checkpw(pwd1, pwd23));
  }
}
