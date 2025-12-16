package com.fitapp.httpServer.application.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import com.fitapp.httpServer.application.cqrs.command.CreateUserCommand;
import com.fitapp.httpServer.application.port.UserRepositoryInterface;
import com.fitapp.httpServer.domain.entity.User;

// TODO: Create DTO
public class UserService {
  UserRepositoryInterface userRepositoryInterface;
  private static final AtomicInteger count = new AtomicInteger(0);

  // The User Interface will be substituted by the
  public UserService(UserRepositoryInterface userRepositoryInterface) {
    this.userRepositoryInterface = userRepositoryInterface;
  }

  // GET all uesr records
  public List<User> findAllUsers() {
    return userRepositoryInterface.findAllUsers();
  }

  // GET user-record by id
  public Optional<User> findUserById(long userId) {
    Optional<User> user = userRepositoryInterface.findUser(userId);
    if (!user.isPresent()) {
      throw new RuntimeException("No user found.");
    }
    return user;
  }

  // CREATE new user record
  public User createUser(CreateUserCommand command) {

    User user = new User(
        command.fname(),
        command.lname(),
        command.password(),
        command.age(),
        command.bodyHeight(),
        command.bodyWeight());
    return userRepositoryInterface.createUser(user);
  }
}
