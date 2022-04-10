package com.daniel.video_game_platform.user.src.domain;

import lombok.Getter;
import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Value(staticConstructor = "of")
public class Username {
  @NotNull
  @Size(min = 4, max = 20)
  String username;
}
