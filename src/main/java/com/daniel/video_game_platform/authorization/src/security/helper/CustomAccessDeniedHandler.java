package com.daniel.video_game_platform.authorization.src.security.helper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
  @Override
  public void handle(
          HttpServletRequest request, HttpServletResponse response, AccessDeniedException exc) {

    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth != null) log.warn(
            "User {} has a wrong authority. Attempted to access the protected URL: {} ",
            auth.getName(),
            request.getRequestURI());
  }
}
