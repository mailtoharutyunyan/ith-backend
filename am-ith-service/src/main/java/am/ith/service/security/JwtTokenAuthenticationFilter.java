package am.ith.service.security;

import am.ith.service.util.ContextHolder;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {

  private final JwtService jwtService;

  @Override
  protected void doFilterInternal(
          final HttpServletRequest request, final HttpServletResponse response, final FilterChain chain)
      throws ServletException, IOException {

    final String token = this.jwtService.getTokenFromHeader(request);
    if (token != null) {

      final Claims claims = this.jwtService.getAllClaimsFromToken(token);

      if (claims != null) {
        ContextHolder.setUserId(claims.get("externalId").toString());
        ContextHolder.setToken(token);

        @SuppressWarnings("unchecked")
        final List<String> authorities = (List<String>) claims.get("roles", List.class);

        final UsernamePasswordAuthenticationToken auth =
            new UsernamePasswordAuthenticationToken(
                null,
                null,
                authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));

        SecurityContextHolder.getContext().setAuthentication(auth);
      }
    }
    try {

      chain.doFilter(request, response);

    } finally {
      ContextHolder.clear();
      SecurityContextHolder.clearContext();
    }
  }
}
