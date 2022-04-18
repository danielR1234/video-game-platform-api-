package com.daniel.video_game_platform.games.src.infrastructure.web.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Set;

@Getter
@AllArgsConstructor
@ToString
public class AddGameRequest {

  String imageUrl;
  private String name;
  private String description;
  private Integer year;
  private Long globalSales;
  private Long publisherId;
  private Long developerId;
  private Set<Long> platformIds;
}
