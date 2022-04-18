package com.daniel.video_game_platform.user.src.application.port.persistance;

import com.daniel.video_game_platform.user.src.domain.PasswordResetToken;
import com.daniel.video_game_platform.user.src.domain.User;

public interface PasswordResetTokenPort {

  PasswordResetToken save(User user);

  User fetchByPasswordResetToken(PasswordResetToken passwordResetToken);
}
