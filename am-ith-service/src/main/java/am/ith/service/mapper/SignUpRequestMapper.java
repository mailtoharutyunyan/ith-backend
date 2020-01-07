package am.ith.service.mapper;

import am.it.api.user.request.SignUpRequest;
import am.ith.service.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.function.Function;

import static am.it.api.user.Role.ROLE_CLIENT;

@Component
public final class SignUpRequestMapper implements Function<SignUpRequest, User> {
  /**
   * Maps {@link SignUpRequest} to {@link User}
   *
   * @param signUpRequest the entity
   * @return the User
   */
  @Override
  public User apply(final SignUpRequest signUpRequest) {
    final User user = new User();
    user.setEmail(signUpRequest.getEmail().toLowerCase(Locale.ENGLISH));
    user.setUsername(signUpRequest.getUserName().toLowerCase(Locale.ENGLISH));
    user.setPassword(signUpRequest.getPassword());
    user.setRoles(new ArrayList<>(Collections.singletonList(ROLE_CLIENT)));
    return user;
  }
}
