package com.daniel.video_game_platform.user.src.infrastructure.persistence;

import com.daniel.video_game_platform.user.src.domain.ERole;
import com.daniel.video_game_platform.user.src.infrastructure.persistence.model.RoleJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<RoleJpaEntity, Long> {
  Set<RoleJpaEntity> findByNameIn(Collection<ERole> name);
}
