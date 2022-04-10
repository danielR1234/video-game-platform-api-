package com.daniel.video_game_platform.games.src.application.port.persistance;

import com.daniel.video_game_platform.games.src.domain.Company;
import com.daniel.video_game_platform.games.src.domain.CompanyIdentifier;

public interface CompanyPort {

  Company fetchById(CompanyIdentifier companyIdentifier);
}
