package com.daniel.video_game_platform.user.src.infrastructure.persistence.model;

import com.daniel.video_game_platform.user.src.domain.ERole;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class RoleJpaEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Enumerated(EnumType.STRING)
  @Column(length = 20)
  private ERole name;

  public RoleJpaEntity(ERole name) {
    this.name = name;
  }

  public ERole getName() {
    return name;
  }
}
