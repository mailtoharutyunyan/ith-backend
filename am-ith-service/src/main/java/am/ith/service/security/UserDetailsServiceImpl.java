package am.ith.service.security;

import am.ith.service.model.User;
import am.ith.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String login) {
    String userName = login.toLowerCase(Locale.ENGLISH);
    final User user =
        userRepository
            .findByEmailOrUsername(userName, userName)
            .orElseThrow(
                () -> new UsernameNotFoundException("Account not found for provided " + userName));
    return new UserDetailsImpl(user);
  }
}
