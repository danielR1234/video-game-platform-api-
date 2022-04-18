package com.daniel.video_game_platform.user.src.domain;

import lombok.Getter;
import lombok.Value;

@Getter
@Value(staticConstructor = "of")
public class IsUserAccountActivated {

  public boolean isAccountActivated;
}
