package com.fitapp.httpServer.domain.value_object;

public class Password {
  private String value;

  public Password(String value) {
    this.value = validateValue(value);
  }

  private String validateValue(String input) {
    if (input.isEmpty()) {
      return null;
    }
    return input;
  }
}
