package com.fitapp.httpServer.application.usecase.user;

import com.fitapp.httpServer.application.port.UserInterface;
import com.fitapp.httpServer.domain.entity.User;

public class CreateUser {
  UserInterface userInterface;

  // Dependency Incjection to Inject the UserAdapter from the Infrastructure Layer
  // into the Usecase to be able to communicate with the DB through the Adapter.
  public CreateUser(UserInterface userInterface) {
    this.userInterface = userInterface;
  }

  public User createUser(User user) {
    return userInterface.createUser(user);
  }
}
