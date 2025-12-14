package com.fitapp.httpServer.application.cqrs.command;

import com.fitapp.httpServer.domain.value_object.Password;

public record CreateUserCommand(String fname, String lname, Password password, int age, int bodyHeight,
    int bodyWeight) {
}
