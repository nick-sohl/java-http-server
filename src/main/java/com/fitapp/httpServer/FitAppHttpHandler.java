package com.fitapp.httpServer;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.Headers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

public class FitAppHttpHandler implements HttpHandler {
  String response;

  public FitAppHttpHandler(String response) {
    this.response = response;
  }

  @Override
  public void handle(HttpExchange httpExchange) throws IOException {
    String method = httpExchange.getRequestMethod();
    Headers headers = httpExchange.getRequestHeaders();
    InputStream body = httpExchange.getRequestBody();

    // Set Response Headers
    Headers responseHeaders = httpExchange.getResponseHeaders();
    responseHeaders.add("Server", "Nicks HTTP Server");
    Date currentDate = new Date();
    responseHeaders.add("Date", currentDate.toString());
    responseHeaders.add("Content-Type", "text/html");
    responseHeaders.add("Access-Control-Allow-Origin", "*");

    if ("GET".equals(method)) {
      httpExchange.sendResponseHeaders(200, response.getBytes().length);
      OutputStream responseBody = httpExchange.getResponseBody();
      responseBody.write(response.getBytes());
      responseBody.close();
      httpExchange.close();
    } else if ("POST".equals(method)) {
      // handle request body
    } else if ("DELETE".equals(method)) {
      // handle delete
    } else {
      httpExchange.sendResponseHeaders(405, -1);
    }
  }
}
