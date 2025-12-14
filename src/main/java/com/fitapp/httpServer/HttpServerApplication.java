package com.fitapp.httpServer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.sun.net.httpserver.HttpServer;
import com.fitapp.httpServer.presentation.api.UserController;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.net.InetSocketAddress;

public class HttpServerApplication {
  public static void main(String[] args) {
    try {
      // Create Http Server
      HttpServer httpServer = HttpServer.create(new InetSocketAddress("localhost", 8000), 0);

      // Define Endpoints
      final String apiRoot = "/api";

      UserController userController = new UserController();
      httpServer.createContext(apiRoot + "/user", userController);
      // httpServer.createContext(apiRoot + "/user" + userId);

      // Start Http Server
      httpServer.start();
    } catch (IOException e) {
      System.out.print(e.getMessage());
    }

  }
}
