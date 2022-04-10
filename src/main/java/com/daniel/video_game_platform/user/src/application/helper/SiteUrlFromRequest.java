package com.daniel.video_game_platform.user.src.application.helper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.servlet.http.HttpServletRequest;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SiteUrlFromRequest {
  public static String getSiteURL(HttpServletRequest request) {
    return request.getRequestURL().toString().replace(request.getServletPath(), "");
  }
}
