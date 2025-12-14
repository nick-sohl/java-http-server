package com.fitapp.httpServer.infrastructure.persistence.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnector {
  private final String PROTOCOL;
  private final String HOST;
  private final int PORT;
  private final String DB_NAME;

  private String DB_USER;
  private String DB_PASSWORD;

  public JdbcConnector(Builder builder) {
    this.PROTOCOL = builder.protocol;
    this.HOST = builder.host;
    this.PORT = builder.port;
    this.DB_NAME = builder.dbName;
    this.DB_USER = builder.dbUser;
    this.DB_PASSWORD = builder.dbPassword;
  }

  private final String buildUrl() {
    return PROTOCOL + "://" + HOST + ":" + PORT + "/" + DB_NAME;
  }

  public Connection establishConnection() {
    String url = buildUrl();
    try {
      return DriverManager.getConnection(url, DB_USER, DB_PASSWORD);
    } catch (SQLException e) {
      System.out.print(e.getMessage());
      return null;
    }
  }

  public static class Builder {
    private String protocol;
    private String host;
    private int port;
    private String dbName;
    private String dbUser;
    private String dbPassword;

    public Builder protocol(String protocol) {
      this.protocol = protocol;
      return this;
    }

    public Builder host(String host) {
      this.host = host;
      return this;
    }

    public Builder port(int port) {
      this.port = port;
      return this;
    }

    public Builder dbName(String dbName) {
      this.dbName = dbName;
      return this;
    }

    public Builder dbUser(String dbUser) {
      this.dbUser = dbUser;
      return this;
    }

    public Builder dbPassword(String dbPassword) {
      this.dbPassword = dbPassword;
      return this;
    }

    public JdbcConnector build() {
      return new JdbcConnector(this);
    }

  }

}
