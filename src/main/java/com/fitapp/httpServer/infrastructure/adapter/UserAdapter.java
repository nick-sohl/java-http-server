package com.fitapp.httpServer.infrastructure.adapter;

import java.util.List;
import java.util.Optional;

import com.fitapp.httpServer.application.cqrs.command.CreateUserCommand;
import com.fitapp.httpServer.application.port.UserInterface;
import com.fitapp.httpServer.domain.entity.User;
import com.fitapp.httpServer.infrastructure.persistence.UserRepository;

public class UserAdapter implements UserInterface {
  UserRepository userRepository;

  // Inject UserRepository
  public UserAdapter(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public Optional<User> findUser(int userId) {
    return userRepository.findUser(userId);
  }

  @Override
  public List<User> findAllUsers() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findAllUsers'");
  }

  @Override
  public User createUser(CreateUserCommand command) {
    userRepository.createUser(command);
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
