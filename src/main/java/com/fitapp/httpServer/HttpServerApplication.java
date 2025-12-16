package com.fitapp.httpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import com.fitapp.httpServer.application.service.UserService;
import com.fitapp.httpServer.infrastructure.adapter.UserRepositoryAdapter;
import com.fitapp.httpServer.infrastructure.persistence.UserRepository;
import com.fitapp.httpServer.presentation.api.UserController;
import com.fitapp.httpServer.presentation.api.WelcomeController;
import com.sun.net.httpserver.HttpServer;

public class HttpServerApplication {

  WelcomeController welcomeController;
  UserController userController;

  public HttpServerApplication(WelcomeController welcomeController, UserController userController) {
    this.welcomeController = welcomeController;
    this.userController = userController;
  }

  private void runServer() throws IOException {
    // Create Http Server
    HttpServer httpServer = HttpServer.create(new InetSocketAddress("localhost", 8000), 0);
    System.out.println("Http Server created! " + "Address: " + httpServer.getAddress());

    httpServer.setExecutor(
        Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()));
    // Routes
    httpServer.createContext("/", welcomeController);
    httpServer.createContext("/user", userController);
    System.out.println("Http Context created!");
    // Start Http Server
    httpServer.start();
    System.out.println("Starting server...");
  }

  public static void main(String[] args) throws IOException {
    System.out.println("Hello World!");
    // Init Repo
    UserRepository userRepository = new UserRepository();
    // Init Adapter which is implementing Interface and using the methods of the
    // Repo to retrieve data from the DB
    UserRepositoryAdapter userRepositoryAdapter = new UserRepositoryAdapter(userRepository);
    // Init Service which is using the Adapter to retrieve data to work with the
    // data
    UserService userService = new UserService(userRepositoryAdapter);
    System.out.println("Users:" + userService.findAllUsers());
    // Init Controller which is our HttpHandler to handle Http Requests
    // Uses the Service to get the computed data
    WelcomeController welcomeController = new WelcomeController("<h1>Welcome to Nicks HTTP Server.</h1>");
    UserController userController = new UserController(userService);
    // Init App, provide UserController and start the Server
    new HttpServerApplication(welcomeController, userController).runServer();
    System.out.println("Server is Running!");
  }
}
