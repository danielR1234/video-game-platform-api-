package com.daniel.video_game_platform.user.src.domain;

import lombok.Getter;

import java.util.UUID;

@Getter
public class PasswordResetToken {

  private final String passwordResetToken;

  public PasswordResetToken(String passwordResetToken) {
    this.passwordResetToken = passwordResetToken;
  }

  public PasswordResetToken(User user) {
    passwordResetToken = UUID.randomUUID().toString();
  }
}
