package com.innowise.servlets_task.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

  private static ConnectionFactory INSTANCE;

  private ConnectionFactory() {

  }

  public static ConnectionFactory getInstance() {
    ConnectionFactory connectionFactoryInstance = INSTANCE;
    if (connectionFactoryInstance == null) {
      synchronized (ConnectionFactory.class) {
        connectionFactoryInstance = INSTANCE;
        if (connectionFactoryInstance == null) {
          INSTANCE = new ConnectionFactory();
          connectionFactoryInstance = INSTANCE;
        }
      }
    }
    return connectionFactoryInstance;
  }

  public Connection getNewConnection() throws SQLException {

    Properties properties = new Properties();

    try (FileInputStream fileInputStream =
        new FileInputStream("src/main/resources/connection_factory.properties")) {

      properties.load(fileInputStream);

      String driverName = properties.getProperty("driverName");
      String url = properties.getProperty("url");
      String user = properties.getProperty("user");
      String password = properties.getProperty("password");

      Class.forName(driverName);
      return DriverManager.getConnection(url, user, password);

    } catch (ClassNotFoundException | IOException e) {
      throw new RuntimeException(e);
    }
  }

}
