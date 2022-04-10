package com.daniel.video_game_platform.user.src.domain;

import lombok.Getter;
import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Value(staticConstructor = "of")
public class Password {

  @Size(min = 6, max = 40)
  @NotNull
  String password;

  public Password(String password) {
    this.password = password;
  }
}
