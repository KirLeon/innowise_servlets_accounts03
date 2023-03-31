package com.innowise.servlets_task.controller;

import com.innowise.servlets_task.utils.JWTSecurityUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(filterName = "AuthorizationFilter", urlPatterns = "/*")
public class AuthorizationFilter extends HttpFilter {

  @Override
  public void doFilter(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    String path = request.getPathInfo();
    if (path.equals("/login")) {
      filterChain.doFilter(request, response);
    } else {
      String headerAuthorization = request.getHeader("Authorization");
      if (headerAuthorization == null || !headerAuthorization.matches("Bearer .+")) {
        response.setContentType("application/json");
        response.setStatus(401);
        PrintWriter out = response.getWriter();
        out.println("Request token is absent");
        return;
      }

      String token = headerAuthorization.replaceAll("(Bearer)", "").trim();
      JWTSecurityUtils jwtService = JWTSecurityUtils.getInstance();
      Jws<Claims> claims;

      try {
        claims = jwtService.verifyUserToken(token);
      } catch (JwtException jwtException) {
        response.setContentType("application/json");
        response.setStatus(401);
        PrintWriter out = response.getWriter();
        out.println("Invalid token");
        return;
      }

      int userAccessLevel = claims.getBody().get("userAccessLevel", Integer.class);
      int userId = claims.getBody().get("userId", Integer.class);
      System.out.println("UAL" + userAccessLevel + "UID" + userId);

      String method = request.getMethod();
      if (!method.equals("GET") && userAccessLevel < 1) {
        response.setContentType("application/json");
        response.setStatus(403);
        PrintWriter out = response.getWriter();
        out.println("You are not allowed to send this request");
        return;
      } else if ((method.equals("PUT") || method.equals("DELETE")) && userAccessLevel < 3) {
        response.setContentType("application/json");
        response.setStatus(403);
        PrintWriter out = response.getWriter();
        out.println("You are not allowed to send this request");
        return;
      }

      request.getSession().setAttribute("UAL", userAccessLevel);
      filterChain.doFilter(request, response);
    }
  }
}
