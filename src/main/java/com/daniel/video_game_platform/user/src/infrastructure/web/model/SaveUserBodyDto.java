package com.daniel.video_game_platform.user.src.infrastructure.web.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SaveUserBodyDto {

  private String username;

  private String email;

  private Set<String> roles;

  private String password;
}
