package com.fitapp.httpServer.application.usecase.user;

import com.fitapp.httpServer.application.port.UserRepositoryInterface;
import com.fitapp.httpServer.domain.entity.User;

public class CreateUser {
  UserRepositoryInterface userRepositoryInterface;

  // Dependency Incjection to Inject the UserAdapter from the Infrastructure Layer
  // into the Usecase to be able to communicate with the DB through the Adapter.
  public CreateUser(UserRepositoryInterface userRepositoryInterface) {
    this.userRepositoryInterface = userRepositoryInterface;
  }

  public User createUser(User user) {
    return userRepositoryInterface.createUser(user);
  }
}
