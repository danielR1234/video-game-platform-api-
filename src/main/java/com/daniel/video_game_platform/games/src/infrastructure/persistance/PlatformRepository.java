package com.daniel.video_game_platform.games.src.infrastructure.persistance;

import com.daniel.video_game_platform.games.src.infrastructure.persistance.model.PlatFormJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlatformRepository extends JpaRepository<PlatFormJpaEntity, Long> {}
