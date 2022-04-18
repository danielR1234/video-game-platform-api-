package com.daniel.video_game_platform.authorization.src.helper;

import com.daniel.video_game_platform.authorization.src.security.services.UserDetailsImpl;
import com.daniel.video_game_platform.user.src.domain.Role;
import com.daniel.video_game_platform.user.src.domain.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.daniel.video_game_platform.user.src.domain.ERole.*;
import static java.util.Map.entry;

public class GetRolesHelper {
  private static final Map<String, Role> roleMappingClass =
      Map.ofEntries(entry("admin", new Role(ADMIN)), entry("author", new Role(AUTHOR)));

  private GetRolesHelper() {}

  public static Set<Role> mapRolesClass(Set<String> strRoles) {
    return strRoles != null
        ? strRoles.stream()
            .map(it -> roleMappingClass.getOrDefault(it, new Role(USER)))
            .collect(Collectors.toSet())
        : Set.of(new Role(USER));
  }

  public static List<String> getRolesFromUser(User user) {
    return user.getRoles().stream().map(role -> role.getName().name()).toList();
  }

  public static List<String> getRolesFromUserDetails(UserDetailsImpl userDetails) {
    return userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
  }
}
