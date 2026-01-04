package com.fitapp.httpServer.presentation.api;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class WelcomeController implements HttpHandler {

  // Get the HTML file from the Classpath
  private static final String RESOURCE = "static/index.html";

  private String readFromResource() {
    try (InputStream is = getClass().getClassLoader().getResourceAsStream(RESOURCE)) {

      if (is == null) {
        throw new RuntimeException("Resource not found: " + RESOURCE);
      }

      return new String(is.readAllBytes(), StandardCharsets.UTF_8);

    } catch (IOException e) {
      throw new RuntimeException("Error reading resource: " + RESOURCE, e);
    }
  }

  @Override
  public void handle(HttpExchange exchange) throws IOException {
    System.out.println("Handle request...");

    String response = readFromResource();
    byte[] bytes = response.getBytes(StandardCharsets.UTF_8);

    Headers headers = exchange.getResponseHeaders();
    headers.add("Access-Control-Allow-Origin", "*");
    headers.add("Content-Type", "text/html; charset=UTF-8");

    exchange.sendResponseHeaders(200, bytes.length);

    try (OutputStream os = exchange.getResponseBody()) {
      os.write(bytes);
    }
  }
}
