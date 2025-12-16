package com.fitapp.httpServer.presentation.api;

import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class WelcomeController implements HttpHandler {
  private final String response;

  public WelcomeController(String response) {
    this.response = response;
  }

  @Override
  public void handle(HttpExchange exchange) throws IOException {
    System.out.println("Handle request...");
    Headers responseHeaders = exchange.getResponseHeaders();
    responseHeaders.add("Access-Control-Allow-Origin", "*");
    responseHeaders.add("Content-Type", "text/html");

    exchange.sendResponseHeaders(200, response.getBytes().length);
    System.out.println("Send Response Headers");
    try (OutputStream os = exchange.getResponseBody()) {
      os.write(response.getBytes());
      System.out.println("Show Data From Response Body");
    }

  }
}
