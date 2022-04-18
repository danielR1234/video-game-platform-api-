package com.daniel.video_game_platform.games.src.infrastructure.persistance.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "platforms")
public class PlatFormJpaEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "createDate", nullable = false)
  private Date createdAt;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "updateDate", nullable = false)
  private Date updateAt;

  @Column(name = "name", unique = true)
  private String name;

  public PlatFormJpaEntity(String name) {
    this.name = name;
  }

  public PlatFormJpaEntity(Long id, String name) {
    this(id, null, null, name);
  }

  @PrePersist
  private void onCreate() {
    createdAt = updateAt = new Date();
  }

  @PreUpdate
  private void onUpdate() {
    updateAt = new Date();
  }
}
