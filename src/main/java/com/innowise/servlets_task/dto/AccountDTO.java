package com.innowise.servlets_task.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.innowise.servlets_task.entity.Departments;
import com.innowise.servlets_task.entity.Rank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO implements DTO{

  @JsonProperty
  @EqualsAndHashCode.Exclude
  private int id;
  @JsonProperty
  private String firstName;
  @JsonProperty
  private String lastName;
  @JsonProperty
  private String password;
  @JsonProperty
  private double salary;
  @JsonProperty("department")
  private Departments department;
  @JsonProperty("rank")
  private Rank rank;
}
