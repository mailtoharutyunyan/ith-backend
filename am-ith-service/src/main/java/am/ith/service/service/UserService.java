package am.ith.service.service;

import am.it.api.dto.user.request.SignUpRequest;
import am.it.api.dto.user.response.SignUpResponse;
import am.ith.service.model.User;

public interface UserService {

  SignUpResponse addUser(SignUpRequest userRegRequest);

  User findByEmailOrUserName(String login);
}
