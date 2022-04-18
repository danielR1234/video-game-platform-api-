package com.daniel.video_game_platform.games.src.infrastructure.persistance;

import com.daniel.video_game_platform.games.src.domain.Game;
import com.daniel.video_game_platform.games.src.infrastructure.persistance.model.GameJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface GameRepository extends JpaRepository<GameJpaEntity, Long> {

  Optional<GameJpaEntity> findByName(String name);

  List<Game> findByPlatformsId(Long id);

  @Query("select (count(g) > 0) from GameJpaEntity g where g.name = ?1")
  boolean existsByName(String name);
}
