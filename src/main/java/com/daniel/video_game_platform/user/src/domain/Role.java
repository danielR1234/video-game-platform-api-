package com.daniel.video_game_platform.user.src.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class Role {

  private final ERole name;
  private Integer id;

  public Role(ERole name) {
    this.name = name;
  }

  public ERole getName() {
    return name;
  }
}
