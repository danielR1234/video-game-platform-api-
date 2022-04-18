package com.daniel.video_game_platform.user.src.application.impl;

import com.daniel.video_game_platform.user.src.application.port.persistance.ReadUserPort;
import com.daniel.video_game_platform.user.src.application.port.persistance.WriteUserPort;
import com.daniel.video_game_platform.user.src.application.service.SubmitNewUserService;
import com.daniel.video_game_platform.user.src.domain.User;
import org.springframework.stereotype.Service;

@Service
public class SubmitNewUserServiceImpl implements SubmitNewUserService {

  private final WriteUserPort writeUserPort;
  private final ReadUserPort readUserPort;

  public SubmitNewUserServiceImpl(WriteUserPort writeUserPort, ReadUserPort readUserPort) {
    this.writeUserPort = writeUserPort;
    this.readUserPort = readUserPort;
  }

  @Override
  public User save(User user) {
    if (readUserPort.existsByUsername(user)) throw new IllegalArgumentException("User already exists");
    return writeUserPort.saveNew(user);
  }
}
