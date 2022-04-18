package com.daniel.video_game_platform.user.src.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class ConfirmationToken {

  private final String confirmationToken;
  private long tokenId;

  public ConfirmationToken(User newUser) {
    confirmationToken = UUID.randomUUID().toString();
  }

  public ConfirmationToken(String confirmationToken) {
    this.confirmationToken = confirmationToken;
  }
}
