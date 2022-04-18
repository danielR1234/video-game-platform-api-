package com.daniel.video_game_platform.user.src.domain;

import lombok.Getter;
import lombok.Value;

import javax.validation.constraints.NotNull;

@Value(staticConstructor = "of")
@Getter
public class UserIdentifier {

  @NotNull Long value;

  Long getLongValue() {
    return value;
  }
}
