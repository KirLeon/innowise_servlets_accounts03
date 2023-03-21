package com.innowise.servlets_task.entity;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Rank {
  EMPLOYEE("Employee"), MANAGER("Manager"), ADMINISTRATOR("Administrator");

  private String rankTitle;

  Rank(String rankTitle) {
    this.rankTitle = rankTitle;
  }

  public String getRankTitle() {
    return rankTitle;
  }

  @JsonCreator
  public static Rank fromString(String rankTitle){
    return valueOf(rankTitle.toUpperCase());
  }
}
