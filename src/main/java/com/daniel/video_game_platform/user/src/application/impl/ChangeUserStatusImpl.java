package com.daniel.video_game_platform.user.src.application.impl;

import com.daniel.video_game_platform.user.src.application.port.persistance.ConfirmationTokenPort;
import com.daniel.video_game_platform.user.src.application.port.persistance.ReadUserPort;
import com.daniel.video_game_platform.user.src.application.port.persistance.WriteUserPort;
import com.daniel.video_game_platform.user.src.application.service.ChangeUserStatusService;
import com.daniel.video_game_platform.user.src.domain.ConfirmationToken;
import com.daniel.video_game_platform.user.src.domain.EmailAddress;
import com.daniel.video_game_platform.user.src.domain.User;
import org.springframework.stereotype.Service;

@Service
public class ChangeUserStatusImpl implements ChangeUserStatusService {

  private final ReadUserPort readUserPort;
  private final ConfirmationTokenPort confirmationTokenPort;
  private final WriteUserPort writeUserPort;

  public ChangeUserStatusImpl(
          ReadUserPort readUserPort,
          ConfirmationTokenPort confirmationTokenPort,
          WriteUserPort writeUserPort) {

    this.readUserPort = readUserPort;
    this.writeUserPort = writeUserPort;
    this.confirmationTokenPort = confirmationTokenPort;
  }

  @Override
  public void activatedUserAccount(ConfirmationToken confirmationToken) {
    confirmationTokenPort.fetchByConfirmationToken(confirmationToken);
    User user =
            readUserPort.fetchByEmail(
            EmailAddress.of(confirmationTokenPort.fetchByConfirmationToken(confirmationToken)));
    user.setEnabled(true);

    writeUserPort.update(user);
  }
}
