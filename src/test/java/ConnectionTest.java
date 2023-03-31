import com.innowise.servlets_task.dao.AccountDAO;
import com.innowise.servlets_task.dao.ConnectionFactory;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ConnectionTest {

  @Test
  public void testConnection() {
    AccountDAO accountDAO = AccountDAO.getInstance();
    Connection connection = null;
    try {
      connection = accountDAO.getNewConnection();
      Assertions.assertTrue(connection.isValid(1));
      Assertions.assertFalse(connection.isClosed());
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

  }

  @Test
  public void ConnectionFactoryTest() {
    ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
    AccountDAO accountDAO = AccountDAO.getInstance();

    try {
      Connection connection = connectionFactory.getNewConnection();
      Assertions.assertTrue(connection.isValid(1));
      Assertions.assertFalse(connection.isClosed());
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
