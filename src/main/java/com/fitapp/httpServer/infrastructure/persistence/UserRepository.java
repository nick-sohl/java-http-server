package com.fitapp.httpServer.infrastructure.persistence;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fitapp.httpServer.domain.entity.User;
import com.fitapp.httpServer.domain.value_object.Password;
import com.fitapp.httpServer.infrastructure.persistence.jdbc.JdbcConnector;

public class UserRepository {
  private static final String USER_TABLE = "users";

  private final JdbcConnector jdbcConnector = new JdbcConnector.Builder()
      .protocol("jdbc:postgresql")
      .host("localhost")
      .port(5432)
      .dbName("fit-app")
      .dbUser("postgres")
      .dbPassword("postgres")
      .build();

  public List<User> findAllUsers() {
    String sql = "SELECT * FROM " + USER_TABLE;
    List<User> users = new ArrayList<>();

    try (Connection conn = jdbcConnector.establishConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
      // pstmt.setString(1, USER_TABLE);
      System.out.println("SQL query: " + pstmt.toString());
      try (ResultSet resultSet = pstmt.executeQuery()) {
        if (resultSet.next()) {

          System.out.println(resultSet.getString("fname") + " " + resultSet.getString("lname"));
          Password password = new Password(resultSet.getString("password"));

          User user = new User(
              resultSet.getLong("user_id"),
              resultSet.getString("fname"),
              resultSet.getString("lname"),
              password,
              resultSet.getInt("age"),
              resultSet.getInt("body_height"),
              resultSet.getInt("body_weight"));

          users.add(user);
        }
      } catch (Exception e) {
        throw new RuntimeException("Failed to find record in result set.");
      }
      return users;
    } catch (SQLException e) {
      throw new RuntimeException("Failed to query user table");
    }
  }

  public Optional<User> findUser(long user_id) {
    String sql = "SELECT * FROM " + USER_TABLE + " WHERE user_id=" + user_id;
    try (Connection conn = jdbcConnector.establishConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
      try (ResultSet resultSet = pstmt.executeQuery()) {
        if (resultSet.next()) {
          User newUser = new User(
              resultSet.getLong("user_id"),
              resultSet.getString("fname"),
              resultSet.getString("lname"),
              new Password(resultSet.getString("password")),
              resultSet.getInt("age"),
              resultSet.getInt("body_height"),
              resultSet.getInt("body_weight"));
          return Optional.of(newUser);
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Failed to query user with id=" + user_id, e);
    }
    return Optional.empty();
  }

  public User createUser(User user) {
    String sql = """
          INSERT INTO user (firstname, lastname, age, body_height, body_weight)
          VALUES (?, ?, ?, ?, ?);
        """;

    try (Connection conn = jdbcConnector.establishConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
      pstmt.setString(1, user.getFname());
      pstmt.setString(2, user.getLname());
      pstmt.setInt(3, user.getAge());
      pstmt.setInt(4, user.getBodyHeight());
      pstmt.setInt(5, user.getBodyWeight());

      pstmt.executeUpdate();

      // Get the ID from the created user
      try (ResultSet keys = pstmt.getGeneratedKeys()) {
        if (keys.next()) {
          long id = keys.getLong(1);
          user.setUserId(id);
          return user;
        }
        throw new SQLException("No ID returned by database");
      }
    } catch (SQLException e) {
      throw new RuntimeException("The user could not be created");
    }
  }
}
