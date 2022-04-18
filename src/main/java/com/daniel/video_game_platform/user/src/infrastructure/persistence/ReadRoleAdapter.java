package com.daniel.video_game_platform.user.src.infrastructure.persistence;

import com.daniel.video_game_platform.user.src.application.port.persistance.ReadRolePort;
import com.daniel.video_game_platform.user.src.domain.Role;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ReadRoleAdapter implements ReadRolePort {

  private final RoleRepository roleRepository;

  public ReadRoleAdapter(RoleRepository roleRepository) {
    this.roleRepository = roleRepository;
  }

  @Override
  public Set<Role> fetchByNameIn(Set<Role> roles) {
    return roleRepository
        .findByNameIn(RoleJpaMapper.getERoleCollectionFromRoleCollection(roles))
        .stream()
        .map(it -> new Role(it.getName(), it.getId()))
        .collect(Collectors.toSet());
  }
}
