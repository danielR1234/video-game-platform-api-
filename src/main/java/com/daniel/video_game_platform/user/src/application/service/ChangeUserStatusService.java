package com.daniel.video_game_platform.user.src.application.service;

import com.daniel.video_game_platform.user.src.domain.ConfirmationToken;

public interface ChangeUserStatusService {

  void activatedUserAccount(ConfirmationToken confirmationToken);
}
