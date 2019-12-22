package am.ith.service.service.user;

import am.it.api.user.request.SignUpRequest;
import am.it.api.user.response.SignUpResponse;
import am.ith.service.model.User;

public interface UserService {

  SignUpResponse addUser(SignUpRequest userRegRequest);

  User findByEmailOrUserName(String login);
}
