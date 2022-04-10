package com.daniel.video_game_platform.games.src.infrastructure.persistance.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "companies")
public class CompanyJpaEntity {

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

  public CompanyJpaEntity(String name) {
    this(null, null, null, name);
  }

  public CompanyJpaEntity(Long id, String name) {
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
