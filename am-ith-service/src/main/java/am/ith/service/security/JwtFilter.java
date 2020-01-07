package am.ith.service.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtFilter extends GenericFilterBean {

  private final JwtService jwtService;
  private final UserDetailsServiceImpl userDetailsService;

  @Override
  public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain filterChain)
      throws IOException, ServletException {
    final String token = this.jwtService.getTokenFromHeader((HttpServletRequest) request);
    if (token != null && this.jwtService.isTokenValid(token)) {
      final String subject = this.jwtService.getSubject(token);
      final UserDetails userDetails = this.userDetailsService.loadUserByUsername(subject);
      final Authentication auth =
          new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
      SecurityContextHolder.getContext().setAuthentication(auth);
    }
    filterChain.doFilter(request, response);
  }
}
