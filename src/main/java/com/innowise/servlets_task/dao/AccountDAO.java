package com.innowise.servlets_task.dao;

import com.innowise.servlets_task.entity.Account;
import com.innowise.servlets_task.entity.Departments;
import com.innowise.servlets_task.entity.Rank;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {

  private static volatile AccountDAO accountDAOInstance;

  private AccountDAO() {
  }

  public static AccountDAO getInstance() {
    AccountDAO localInstance = accountDAOInstance;
    if (localInstance == null) {
      synchronized (AccountDAO.class) {
        localInstance = accountDAOInstance;
        if (localInstance == null) {
          accountDAOInstance = new AccountDAO();
          localInstance = accountDAOInstance;
        }
      }
    }
    return localInstance;
  }

  public Connection getNewConnection() throws SQLException {

    try {
      Class.forName("com.mysql.jdbc.Driver");
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
    String url = "jdbc:mysql://localhost/accounts";
    String user = "account_admin";
    String password = "946284";

    return DriverManager.getConnection(url, user, password);
  }

  public Account createNewAccount(Account employee) {

    try (Connection connection = getNewConnection()) {

      String sqlInsert = "INSERT INTO employees (first_name, last_name, department,"
          + " password, salary, emp_rank) VALUES (?, ?, ?, ?, ?, ?)";

      PreparedStatement insertStatement = connection.prepareStatement(sqlInsert);

      insertStatement.setString(1, employee.getFirstName());
      insertStatement.setString(2, employee.getLastName());
      insertStatement.setString(3, employee.getDepartment().getDepartmentName());
      insertStatement.setString(4, employee.getPassword());
      insertStatement.setDouble(5, employee.getSalary());
      insertStatement.setString(6, employee.getRank().toString());

      int rowsInserted = insertStatement.executeUpdate();
      if (rowsInserted > 0) {
        System.out.println("Account created successfully");
        return employee;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return null;
  }

  public Account selectAccount(int id) {

    try (Connection connection = getNewConnection()) {

      String sqlSelectOne = "SELECT * from employees where id = ?";
      PreparedStatement selectOneStatement = connection.prepareStatement(sqlSelectOne);

      selectOneStatement.setInt(1, id);

      ResultSet resultSet = selectOneStatement.executeQuery();
      resultSet.next();

      Account selectedEmployee = Account.builder().build();

      selectedEmployee = setEmployeeParams(resultSet, selectedEmployee);

      return selectedEmployee;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public List<Account> selectAllEmployees() {
    try (Connection connection = getNewConnection()) {

      String sqlSelectAll = "SELECT * from employees";
      Statement statement = connection.createStatement();

      ResultSet resultSet = statement.executeQuery(sqlSelectAll);
      List<Account> employeeList = new ArrayList<>();
      Account currentEmployee;

      while (resultSet.next()) {
        currentEmployee = Account.builder().build();

        currentEmployee = setEmployeeParams(resultSet, currentEmployee);

        employeeList.add(currentEmployee);
      }

      return employeeList;

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public Account updateAccount(Account employee) {

    try (Connection connection = getNewConnection()) {

      String sqlUpdate = "UPDATE employees SET first_name = ?, last_name = ?, department = ?,"
          + " password = ?, salary = ?, emp_rank = ? WHERE id = ?";

      PreparedStatement updateStatement = connection.prepareStatement(sqlUpdate);

      updateStatement.setString(1, employee.getFirstName());
      updateStatement.setString(2, employee.getLastName());
      updateStatement.setString(3, employee.getDepartment().getDepartmentName());

      updateStatement.setString(4, employee.getPassword());
      updateStatement.setDouble(5, employee.getSalary());
      updateStatement.setString(6, employee.getRank().name());

      updateStatement.setInt(7, employee.getId());

      int rowsInserted = updateStatement.executeUpdate();
      if (rowsInserted > 0) {
        System.out.println("Account created successfully");
        return employee;
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public Account deleteEmployee(int id) {
    try (Connection connection = getNewConnection()) {

      String sqlDelete = "DELETE FROM employees where id = ?";
      PreparedStatement deleteStatement = connection.prepareStatement(sqlDelete);

      Account deletedEmployee = selectAccount(id);
      deleteStatement.setInt(1, id);

      int rowsDeleted = deleteStatement.executeUpdate();
      if (rowsDeleted > 0) {
        return deletedEmployee;
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public Account setEmployeeParams(ResultSet resultSet, Account employee)
      throws SQLException {

    employee.setId(resultSet.getInt(1));
    employee.setFirstName(resultSet.getString(2));
    employee.setLastName(resultSet.getString(3));

    employee
        .setDepartment(Departments.valueOf(resultSet.getString(4).toUpperCase()));

    employee.setPassword(resultSet.getString(5));

    employee.setSalary(resultSet.getDouble(6));
    employee.setRank(Rank.valueOf(resultSet.getString(7).toUpperCase()));

    return employee;
  }
}
