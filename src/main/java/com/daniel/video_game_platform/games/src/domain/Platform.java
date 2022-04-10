package com.daniel.video_game_platform.games.src.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@AllArgsConstructor
public class Platform {
  @NotNull PlatformIdentifier platformIdentifier;
  @NotNull String platFormName;

  public Platform(String platFormName) {
    this(null, platFormName);
  }

  public Platform(PlatformIdentifier platformIdentifier) {
    this(platformIdentifier, null);
  }
}
