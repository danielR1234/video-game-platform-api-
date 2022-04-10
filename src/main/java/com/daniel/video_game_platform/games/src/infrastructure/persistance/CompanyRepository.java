package com.daniel.video_game_platform.games.src.infrastructure.persistance;

import com.daniel.video_game_platform.games.src.infrastructure.persistance.model.CompanyJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<CompanyJpaEntity, Long> {}
