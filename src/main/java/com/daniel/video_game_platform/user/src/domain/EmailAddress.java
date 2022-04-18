package com.daniel.video_game_platform.user.src.domain;

import lombok.Getter;
import lombok.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Value(staticConstructor = "of")
public class EmailAddress {

  @NotNull @Email String email;
}
