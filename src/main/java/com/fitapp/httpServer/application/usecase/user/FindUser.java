package com.fitapp.httpServer.application.usecase.user;

import java.util.Optional;

import com.fitapp.httpServer.application.port.UserInterface;
import com.fitapp.httpServer.domain.entity.User;

public class FindUser {
  UserInterface userInterface;

  public FindUser(UserInterface userInterface) {
    this.userInterface = userInterface;
  }

  public Optional<User> findUser(int userId) {
    Optional<User> user = userInterface.findUser(userId);
    return user;
  }
}
