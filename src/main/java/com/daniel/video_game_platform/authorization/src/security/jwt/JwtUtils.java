package com.daniel.video_game_platform.authorization.src.security.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Slf4j
@Component
public class JwtUtils {

  @Value("${daniel.app.jwtSecret}")
  private String jwtSecret;

  @Value("${daniel.app.jwtExpirationMs}")
  private long jwtExpirationMs;

  @Value("${daniel.app.jwtRefreshExpirationMs}")
  private long jwtRefreshExpirationMs;

  static String resolveToken(HttpServletRequest req) {
    String bearerToken = req.getHeader("Authorization");

    final var bearerTokenPrefix = "Bearer ";
    if (bearerToken != null && bearerToken.startsWith(bearerTokenPrefix))
      return bearerToken.substring(bearerTokenPrefix.length());
    return null;
  }

  public String generateJwtAccessToken(String username) {
    return generateTokenFromUsername(username, jwtExpirationMs);
  }

  public String generateJwtRefreshToken(String username) {
    return generateTokenFromUsername(username, jwtRefreshExpirationMs);
  }

  private String generateTokenFromUsername(String username, Long expiration) {
    return Jwts.builder()
        .setSubject(username)
        .setIssuedAt(new Date())
        .setExpiration(new Date((new Date()).getTime() + expiration))
        .signWith(secretKey())
        .compact();
  }

  public String getUserNameFromJwtToken(String token) {
    return Jwts.parserBuilder()
        .setSigningKey(secretKey())
        .build()
        .parseClaimsJws(token)
        .getBody()
        .getSubject();
  }

  public boolean validateJwtToken(String token) {
    try {
      Jwts.parserBuilder().setSigningKey(secretKey()).build().parseClaimsJws(token);
      return true;
    } catch (SignatureException e) {
      JwtUtils.log.error("Invalid JWT signature: {}", e.getMessage());
    } catch (MalformedJwtException e) {
      JwtUtils.log.error("Invalid JWT token: {}", e.getMessage());
    } catch (ExpiredJwtException e) {
      JwtUtils.log.error("JWT token is expired: {}", e.getMessage());
    } catch (UnsupportedJwtException e) {
      JwtUtils.log.error("JWT token is unsupported: {}", e.getMessage());
    } catch (IllegalArgumentException e) {
      JwtUtils.log.error("JWT claims string is empty: {}", e.getMessage());
    }
    return false;
  }

  private SecretKey secretKey() {
    return Keys.hmacShaKeyFor(jwtSecret.getBytes());
  }
}
