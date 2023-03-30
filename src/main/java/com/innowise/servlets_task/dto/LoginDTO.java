package com.innowise.servlets_task.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.innowise.servlets_task.entity.Rank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO implements DTO{
  @JsonProperty
  private int userId;
  @JsonProperty
  private String password;
  private Rank rank;
}
