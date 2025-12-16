package com.fitapp.httpServer.application.usecase.user;

import java.util.Optional;

import com.fitapp.httpServer.application.port.UserRepositoryInterface;
import com.fitapp.httpServer.domain.entity.User;

public class FindUser {
  UserRepositoryInterface userRepositoryInterface;

  public FindUser(UserRepositoryInterface userRepositoryInterface) {
    this.userRepositoryInterface = userRepositoryInterface;
  }

  public Optional<User> findUser(int userId) {
    Optional<User> user = userRepositoryInterface.findUser(userId);
    return user;
  }
}
