package am.ith.service.service.user.impl;

import am.it.api.user.request.SignUpRequest;
import am.it.api.user.response.SignUpResponse;
import am.ith.service.entity.ResponseStatus;
import am.ith.service.exception.AccountExistsException;
import am.ith.service.mapper.SignUpRequestMapper;
import am.ith.service.model.User;
import am.ith.service.repository.UserRepository;
import am.ith.service.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final SignUpRequestMapper signUpRequestMapper;

  @Override
  @Transactional
  public SignUpResponse addUser(final SignUpRequest request) {

    if (this.userRepository.existsByEmail(request.getEmail())) {
      log.info(
          "Calling addUser for {}, {} and getting duplicate email",
          request.getEmail(),
          request.getUserName());
      throw new AccountExistsException(ResponseStatus.EMAIL_EXISTS.getMessage());
    }

    if (this.userRepository.existsByUsername(request.getUserName())) {
      log.info(
          "Calling addUser for {}, {} and getting duplicate username",
          request.getEmail(),
          request.getUserName());
      throw new AccountExistsException(ResponseStatus.USER_NAME_EXISTS.getMessage());
    }

    final User user = this.signUpRequestMapper.apply(request);
    user.setPassword(this.passwordEncoder.encode(request.getPassword()));
    this.userRepository.save(user);

    return new SignUpResponse(ResponseStatus.ACCOUNT_CREATED.getMessage());
  }

  @Override
  @Transactional(readOnly = true)
  public User findByEmailOrUserName(final String userName) {
    return this.userRepository
        .findByEmailOrUsername(userName, userName)
        .orElseThrow(
            () -> new UsernameNotFoundException("Account not found for provided " + userName));
  }
}
