package com.daniel.video_game_platform.user.src.application.port.persistance;

import com.daniel.video_game_platform.user.src.domain.ConfirmationToken;
import com.daniel.video_game_platform.user.src.domain.User;

public interface ConfirmationTokenPort {

  ConfirmationToken saveNew(User user);

  String fetchByConfirmationToken(ConfirmationToken confirmationToken);
}
