package am.ith.service.security;

import am.it.api.user.Role;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class JwtService {

  @Value(value = "${jwt.secret.key}")
  private String secretKey;

  @Value(value = "${jwt.expiration.time}")
  private long expirationTimeInMs;

  @Value(value = "${jwt.header}")
  private String authorization;

  @Value(value = "${jwt.header.key}")
  private String bearer;

  private String encodedKey;

  public String generateToken(final List<Role> roles, String userExternalId) {
    Claims claims = Jwts.claims();
    claims.put("roles", roles);
    claims.put("externalId", userExternalId);
    return Jwts.builder()
        .setClaims(claims)
        .setIssuedAt(new Date())
        .setExpiration(generateExpirationDate())
        .signWith(SignatureAlgorithm.HS256, encodedKey)
        .compact();
  }

  @PostConstruct
  protected void init() {
    encodedKey = Base64.getEncoder().encodeToString(secretKey.getBytes(StandardCharsets.UTF_8));
  }

  public Claims getAllClaimsFromToken(final String token) {
    Claims claims = null;
    try {
      claims = Jwts.parser().setSigningKey(encodedKey).parseClaimsJws(token).getBody();
    } catch (ExpiredJwtException e) {

      log.warn("Expired JWT token for: {}", token);

    } catch (Exception e) {
      log.error("Can`t parse JWT TOKEN, token is: " + token, e);
    }
    return claims;
  }

  private Date generateExpirationDate() {
    return new Date(new Date().getTime() + expirationTimeInMs);
  }

  /**
   * @param token = ""
   * @return boolean
   */
  public boolean isTokenValid(String token) {
    try {
      Jwts.parser().setSigningKey(encodedKey).parseClaimsJws(token);
      return true;
    } catch (JwtException | IllegalArgumentException ex) {
      return false;
    }
  }

  public String getSubject(String token) {
    return Jwts.parser().setSigningKey(encodedKey).parseClaimsJws(token).getBody().getSubject();
  }

  public String getTokenFromHeader(HttpServletRequest request) {
    String bearerToken = request.getHeader(authorization);
    if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(bearer)) {
      return bearerToken.substring(bearer.length() + 1);
    }
    return null;
  }
}
