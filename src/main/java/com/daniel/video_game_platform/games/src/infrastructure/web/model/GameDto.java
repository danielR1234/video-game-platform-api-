package com.daniel.video_game_platform.games.src.infrastructure.web.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@AllArgsConstructor
public class GameDto {

  private Long id;
  private Date createdAt;
  private Date updateAt;
  private String name;
  private String description;
  private Integer year;
  private Long globalSales;
  private String imageUrl;
  private CompanyDto publisher;
  private CompanyDto developer;
  private Set<PlatformDto> platforms = new HashSet<>();
}
