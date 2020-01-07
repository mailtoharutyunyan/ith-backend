package am.ith.service.security;

import am.ith.service.entity.ResponseStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
  @Override
  public void commence(
          final HttpServletRequest httpServletRequest,
          final HttpServletResponse httpServletResponse,
          final AuthenticationException e)
      throws IOException {
    httpServletResponse.sendError(
        HttpServletResponse.SC_UNAUTHORIZED, ResponseStatus.AUTHORIZATION_ERROR.getMessage());
  }
}
