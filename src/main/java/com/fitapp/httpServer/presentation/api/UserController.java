package com.fitapp.httpServer.presentation.api;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.Date;

import com.fitapp.httpServer.application.port.UserInterface;
import com.fitapp.httpServer.application.service.UserService;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class UserController implements HttpHandler {
  UserInterface userInterface;

  public UserController(UserInterface userInterface) {
    this.userInterface = userInterface;
  }

  @Override
  public void handle(HttpExchange exchange) throws IOException {
    // Gathering Infos
    String method = exchange.getRequestMethod();
    Headers requestHeaders = exchange.getRequestHeaders();
    InputStream body = exchange.getRequestBody();

    // Get the uri of the request to determine the userId -> /user/5
    URI uri = exchange.getRequestURI();
    String path = uri.getPath();

    // Set Infors
    Headers responseHeaders = exchange.getResponseHeaders();
    responseHeaders.add("Server", "Nicks HTTP Server");
    responseHeaders.add("Date", new Date().toString());
    responseHeaders.add("Content-Type", "application/json");
    responseHeaders.add("Access-Control-Allow-Origin", "*");

    // TODO: Finish
    if ("GET".equals(method)) {
      exchange.sendResponseHeaders(200, userInterface.findUser(userId).getBytes().length);
      OutputStream responseBody = exchange.getResponseBody();
      responseBody.write(response.getBytes());
      responseBody.close();
      exchange.close();
    }
  }
  // define endpoint, handle http request, build http response

}
