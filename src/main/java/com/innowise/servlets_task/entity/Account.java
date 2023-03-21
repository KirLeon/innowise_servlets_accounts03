package com.innowise.servlets_task.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
public class Account {

  @EqualsAndHashCode.Exclude
  private int id;

  private String firstName;

  private String lastName;

  private String password;

  private double salary;

  private Departments department;

  private Rank rank;

}
