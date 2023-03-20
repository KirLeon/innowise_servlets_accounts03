package com.innowise.servlets_task.controller.dispatcher;

import com.innowise.servlets_task.controller.commands.Commands;
import com.innowise.servlets_task.controller.commands.ServletExecutable;
import com.innowise.servlets_task.controller.servlets.ErrorServlet;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "helloServlet", value = "/*")
public class DispatcherServlet extends HttpServlet {

  public void init() {

  }

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    String path = request.getPathInfo();
    ServletExecutable servlet = new ErrorServlet();

    for(Commands commands : Commands.values()){
      System.out.println("path " + path);
      System.out.println("servlet_path " + commands.getUrlPath());
      System.out.println("true " + path.equals(commands.getUrlPath()));
      if(commands.getUrlPath().equals(path)){
        servlet = commands.getServlet();
        break;
      }
    }

    servlet.execute(request, response);
  }

  public void destroy() {
  }
}