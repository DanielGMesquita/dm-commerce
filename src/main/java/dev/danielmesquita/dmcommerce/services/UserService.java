package dev.danielmesquita.dmcommerce.services;

import dev.danielmesquita.dmcommerce.models.Role;
import dev.danielmesquita.dmcommerce.models.User;
import dev.danielmesquita.dmcommerce.projections.UserDetailsProjection;
import dev.danielmesquita.dmcommerce.repositories.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

  @Autowired private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    List<UserDetailsProjection> result = userRepository.findUserDetailsByEmail(username);
    if (result.isEmpty()) {
      throw new UsernameNotFoundException("User not found with email: " + username);
    }
    User user = new User();
    user.setEmail(username);
    user.setPassword(result.get(0).getPassword());
    result.stream()
        .map(
            projection -> {
              Role role = new Role();
              role.setId(projection.getRoleId());
              role.setAuthority(projection.getAuthority());
              return role;
            })
        .forEach(user::addRole);

    return user;
  }
}
