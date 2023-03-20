package com.innowise.servlets_task.controller.servlets;

import com.innowise.servlets_task.controller.commands.ServletExecutable;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddAccountServlet implements ServletExecutable {

  @Override
  public void execute(HttpServletRequest request, HttpServletResponse response) {
    response.setContentType("text/html");
    try {
      PrintWriter out = response.getWriter();
      out.println("<html><body>");
      out.println("<h1>" + "</h1>");
      out.println("</body></html>");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
