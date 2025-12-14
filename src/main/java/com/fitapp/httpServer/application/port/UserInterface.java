package com.fitapp.httpServer.application.port;

import java.util.List;
import java.util.Optional;

import com.fitapp.httpServer.application.cqrs.command.CreateUserCommand;
import com.fitapp.httpServer.domain.entity.User;

public interface UserInterface {
  Optional<User> findUser(int userId);

  List<User> findAllUsers();

  User createUser(CreateUserCommand command);

  User updateUser();

  void deleteUser();

}
