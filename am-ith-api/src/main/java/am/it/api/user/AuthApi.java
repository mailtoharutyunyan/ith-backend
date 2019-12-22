package am.it.api.user;

import am.it.api.user.request.SignInRequest;
import am.it.api.user.request.SignUpRequest;
import am.it.api.user.response.SignInResponse;
import am.it.api.user.response.SignUpResponse;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@Api(value = "API endpoints to model", tags = "Teacher")
public interface AuthApi {

  @PostMapping(value = "/signup")
  ResponseEntity<SignUpResponse> register(SignUpRequest userRequestDto);

  @PostMapping(value = "/signin")
  ResponseEntity<SignInResponse> login(SignInRequest signInRequest);
}
