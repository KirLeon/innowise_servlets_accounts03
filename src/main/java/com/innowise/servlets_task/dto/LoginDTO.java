package com.innowise.servlets_task.dto;

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
  private int userId;
  private String password;
  private Rank rank;
}
