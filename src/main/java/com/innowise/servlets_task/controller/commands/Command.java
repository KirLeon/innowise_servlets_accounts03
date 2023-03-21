package com.innowise.servlets_task.controller.commands;

import com.innowise.servlets_task.dto.DTO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {

  void executeGet(HttpServletRequest servletRequest, HttpServletResponse servletResponse);

  void executePost(HttpServletRequest servletRequest, HttpServletResponse servletResponse);

  void executeUpdate(HttpServletRequest servletRequest, HttpServletResponse servletResponse);

  void executeDelete(HttpServletRequest servletRequest, HttpServletResponse servletResponse);

  DTO parseFromJson(HttpServletRequest servletRequest);

  void printResponseJSON(String responseKey, String responseValue,
      HttpServletResponse servletResponse);
}
