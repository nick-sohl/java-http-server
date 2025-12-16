package com.fitapp.httpServer.application.port;

import java.util.List;
import java.util.Optional;

import com.fitapp.httpServer.domain.entity.User;

public interface UserRepositoryInterface {
  Optional<User> findUser(long userId);

  List<User> findAllUsers();

  User createUser(User user);

  User updateUser();

  void deleteUser();

}
