package com.daniel.video_game_platform.authorization.src;

import com.daniel.video_game_platform.authorization.src.security.helper.CustomAccessDeniedHandler;
import com.daniel.video_game_platform.authorization.src.security.jwt.JwtAuthorizationFilterConfigurer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

import static com.daniel.video_game_platform.user.src.domain.ERole.ADMIN;
import static com.daniel.video_game_platform.user.src.domain.ERole.AUTHOR;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired private JwtAuthorizationFilterConfigurer jwtAuthorizationFilterConfigurer;

  @Bean
  private static PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(12);
  }

  @Bean
  private static AccessDeniedHandler accessDeniedHandler() {
    return new CustomAccessDeniedHandler();
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http.cors()
        .and()
        .csrf()
        .disable()
        // No session will be created or used by spring security
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/v1/user").hasAuthority(ADMIN.name());
    http.authorizeRequests().antMatchers("/api/v1/games/**").hasAuthority(AUTHOR.name());
    http.exceptionHandling().accessDeniedHandler(WebSecurityConfig.accessDeniedHandler());
    http.authorizeRequests()
        .antMatchers(
            "/api/v1/auth/**",
            "/api/v1/user/confirmAccount",
            "/api/v1/user/resetPassword",
            "/api/v1/user/changePassword")
        .permitAll()
        .anyRequest()
        .authenticated();

    // Apply JWT
    http.apply(jwtAuthorizationFilterConfigurer);
  }
}
