package com.daniel.video_game_platform.user.src.application.impl;

import com.daniel.video_game_platform.user.src.application.port.persistance.ReadUserPort;
import com.daniel.video_game_platform.user.src.application.service.GetUserDataService;
import com.daniel.video_game_platform.user.src.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetUserDataServiceImpl implements GetUserDataService {

  private final ReadUserPort readUserPort;

  public GetUserDataServiceImpl(ReadUserPort readUserPort) {
    this.readUserPort = readUserPort;
  }

  @Override
  public List<User> fetchAll() {
    return readUserPort.fetchAll();
  }
}
