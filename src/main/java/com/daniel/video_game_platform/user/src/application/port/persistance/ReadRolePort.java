package com.daniel.video_game_platform.user.src.application.port.persistance;

import com.daniel.video_game_platform.user.src.domain.Role;

import java.util.Set;

public interface ReadRolePort {

  Set<Role> fetchByNameIn(Set<Role> roles);
}
