package am.ith.service.service;

import am.it.api.dto.user.request.SignUpRequest;
import am.it.api.dto.user.response.SignUpResponse;
import am.ith.service.entity.ResponseStatus;
import am.ith.service.exception.AccountExistsException;
import am.ith.service.mapper.SignUpRequestMapper;
import am.ith.service.model.User;
import am.ith.service.repository.UserRepository;
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

  @Transactional
  public SignUpResponse addUser(SignUpRequest request) {

    if (userRepository.existsByEmail(request.getEmail())) {
      log.info(
          "Calling addUser for {}, {} and getting duplicate email",
          request.getEmail(),
          request.getUserName());
      throw new AccountExistsException(ResponseStatus.EMAIL_EXISTS.getMessage());
    }

    if (userRepository.existsByUsername(request.getUserName())) {
      log.info(
          "Calling addUser for {}, {} and getting duplicate username",
          request.getEmail(),
          request.getUserName());
      throw new AccountExistsException(ResponseStatus.USER_NAME_EXISTS.getMessage());
    }

    User user = signUpRequestMapper.apply(request);
    user.setPassword(passwordEncoder.encode(request.getPassword()));
    userRepository.save(user);

    return new SignUpResponse(ResponseStatus.ACCOUNT_CREATED.getMessage());
  }

  @Override
  @Transactional(readOnly = true)
  public User findByEmailOrUserName(final String userName) {
    return userRepository
        .findByEmailOrUsername(userName, userName)
        .orElseThrow(
            () -> new UsernameNotFoundException("Account not found for provided " + userName));
  }
}
