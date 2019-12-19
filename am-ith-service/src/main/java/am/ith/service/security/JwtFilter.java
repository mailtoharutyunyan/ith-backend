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
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
      throws IOException, ServletException {
    String token = jwtService.getTokenFromHeader((HttpServletRequest) request);
    if (token != null && jwtService.isTokenValid(token)) {
      String subject = jwtService.getSubject(token);
      UserDetails userDetails = userDetailsService.loadUserByUsername(subject);
      Authentication auth =
          new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
      SecurityContextHolder.getContext().setAuthentication(auth);
    }
    filterChain.doFilter(request, response);
  }
}
