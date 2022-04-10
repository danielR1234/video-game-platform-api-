package com.daniel.video_game_platform.authorization.src.infrastructure.web.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
public class SignUpRequest {

  @NotBlank private String username;

  @NotBlank
  @Size(max = 50)
  @Email
  private String email;

  private Set<String> roles;

  @Size(min = 6, max = 40)
  private String password;
}
