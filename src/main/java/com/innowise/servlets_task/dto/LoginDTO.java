package com.innowise.servlets_task.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.innowise.servlets_task.entity.Rank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class LoginDTO implements DTO{
  @JsonProperty
  private int userId;
  @JsonProperty
  private String password;
  @JsonProperty
  private Rank rank;
}
