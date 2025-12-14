package com.fitapp.httpServer.infrastructure.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

import com.fitapp.httpServer.application.cqrs.command.CreateUserCommand;
import com.fitapp.httpServer.domain.entity.User;
import com.fitapp.httpServer.infrastructure.persistence.jdbc.JdbcConnector;

public class UserRepository {
  private static final String USER_TABLE = "user";

  private final JdbcConnector jdbcConnector = new JdbcConnector.Builder()
      .protocol("jdbc:postgresql")
      .host("localhost")
      .dbName("fit-app")
      .dbUser("postgres")
      .dbPassword("postgres")
      .build();

  public Optional<User> findUser(int userId) {
    String sql = "SELECT * FROM " + USER_TABLE + " WHERE user_id=" + userId;
    try (Connection conn = jdbcConnector.establishConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
      ResultSet resultSet = pstmt.executeQuery();
      Optional<User> object = (Optional<User>) resultSet.getObject(userId);
      return object;
    } catch (Exception e) {
      System.out.print(e.getMessage());
      return null;
    }
  }

  public void createUser(CreateUserCommand command) {
    String sql = """
          INSERT INTO user (firstname, lastname, age, body_height, body_weight)
          VALUES (?, ?, ?, ?, ?);
        """;

    try (Connection conn = jdbcConnector.establishConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
      pstmt.setString(1, command.fname());
      pstmt.setString(2, command.lname());
      pstmt.setInt(3, command.age());
      pstmt.setInt(4, command.bodyHeight());
      pstmt.setInt(5, command.bodyWeight());

      pstmt.execute();
    } catch (SQLException e) {
      System.out.print(e.getMessage());
    }
  }
}
