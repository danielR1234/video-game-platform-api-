package com.daniel.video_game_platform.user.src.infrastructure.persistence;

import com.daniel.video_game_platform.user.src.domain.ERole;
import com.daniel.video_game_platform.user.src.domain.Role;
import com.daniel.video_game_platform.user.src.domain.User;
import com.daniel.video_game_platform.user.src.infrastructure.persistence.model.RoleJpaEntity;

import java.util.Set;
import java.util.stream.Collectors;

class RoleJpaMapper {

  private RoleJpaMapper() {}

  static Set<RoleJpaEntity> getRoleJpaCollectionFromUserRoleCollection(User user) {
    return user.getRoles().stream()
        .map(it -> new RoleJpaEntity(it.getId(), it.getName()))
        .collect(Collectors.toSet());
  }

  static Set<ERole> getERoleCollectionFromRoleCollection(Set<Role> roles) {
    return roles.stream().map(Role::getName).collect(Collectors.toSet());
  }
}
