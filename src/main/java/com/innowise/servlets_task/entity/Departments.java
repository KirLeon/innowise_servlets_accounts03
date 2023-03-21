package com.innowise.servlets_task.entity;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Departments {
  HR("HR"), IT("IT"), SALES("Sales"),
  ADMINISTRATION("Administration"), SUPPORT("Support"),
  FINANCE("Finance");

  private final String departmentName;

  Departments(String departmentName) {
    this.departmentName = departmentName;
  }

  public String getDepartmentName() {
    return departmentName;
  }

  @JsonCreator
  public static Departments fromString(String departmentName) {
    return valueOf(departmentName.toUpperCase());
  }
}
