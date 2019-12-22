package am.ith.server.auth;

import am.it.api.user.AuthApi;
import am.it.api.user.request.SignInRequest;
import am.it.api.user.request.SignUpRequest;
import am.it.api.user.response.SignInResponse;
import am.it.api.user.response.SignUpResponse;
import am.ith.service.model.User;
import am.ith.service.security.JwtService;
import am.ith.service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/auth")
class AuthApiImpl implements AuthApi {

  private final UserService userService;
  private final AuthenticationManager authenticationManager;
  private final JwtService jwtService;

  public ResponseEntity<SignUpResponse> register(@Valid @RequestBody SignUpRequest request) {
    log.info("Calling signUp for {} {}", request.getEmail(), request.getUserName());
    SignUpResponse signUpResponse = userService.addUser(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(signUpResponse);
  }

  @Override
  public ResponseEntity<SignInResponse> login(@Valid @RequestBody SignInRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword()));
    User user = userService.findByEmailOrUserName(request.getLogin());
    String token = jwtService.generateToken(user.getRoles(), user.getExternalId());
    return ResponseEntity.ok(new SignInResponse(token));
  }
}
