package com.fitapp.httpServer.infrastructure.adapter;

import java.util.List;
import java.util.Optional;

import com.fitapp.httpServer.application.cqrs.command.CreateUserCommand;
import com.fitapp.httpServer.application.port.UserRepositoryInterface;
import com.fitapp.httpServer.domain.entity.User;
import com.fitapp.httpServer.infrastructure.persistence.UserRepository;

public class UserRepositoryAdapter implements UserRepositoryInterface {
  UserRepository userRepository;

  // Inject UserRepository
  public UserRepositoryAdapter(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public Optional<User> findUser(long userId) {
    return userRepository.findUser(userId);
  }

  @Override
  public List<User> findAllUsers() {
    return userRepository.findAllUsers();
  }

  @Override
  public User createUser(User user) {
    return userRepository.createUser(user);
  }

  @Override
  public User updateUser() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'updateUser'");
  }

  @Override
  public void deleteUser() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'deleteUser'");
  }

}
