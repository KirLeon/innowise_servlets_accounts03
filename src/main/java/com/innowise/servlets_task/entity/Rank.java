package com.innowise.servlets_task.entity;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Rank {
  EMPLOYEE(1), MANAGER(2), ADMINISTRATOR(3);

  private final int rankAccessLevel;

  Rank(int rankAccessLevel) {
    this.rankAccessLevel = rankAccessLevel;
  }

  public int getRankAccessLevel() {
    return rankAccessLevel;
  }

  @JsonCreator
  public static Rank fromString(String rankTitle) {
    return valueOf(rankTitle.toUpperCase());
  }
}
