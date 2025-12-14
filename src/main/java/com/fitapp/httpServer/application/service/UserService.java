package com.fitapp.httpServer.application.service;

import java.util.Optional;

import com.fitapp.httpServer.application.cqrs.command.CreateUserCommand;
// application -> usecase
import com.fitapp.httpServer.application.usecase.user.CreateUser;
import com.fitapp.httpServer.application.usecase.user.FindUser;
// domain -> entity
import com.fitapp.httpServer.domain.entity.User;

public class UserService {
  // Reference Usecase
  FindUser findUser;
  CreateUser createUser;

  // Inject Usecase through Constructor
  public UserService(CreateUser createUser, FindUser findUser) {
    this.findUser = findUser;
    this.createUser = createUser;
  }

  // Usecases
  // GET user-record by id
  public Optional<User> findUserById(int userId) {
    return findUser.findUser(userId);
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
    return createUser.createUser(user);
  }
}
