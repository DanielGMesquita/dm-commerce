package dev.danielmesquita.dmcommerce.services;

import dev.danielmesquita.dmcommerce.entities.User;
import dev.danielmesquita.dmcommerce.services.exceptions.ForbiddenException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

  private final UserService userService;

  public AuthService(UserService userService) {
    this.userService = userService;
  }

  public void validateSelfOrAdmin(Long userId) {
    User me = userService.authenticated();
    if (me.hasRole("ROLE_ADMIN")) {
      return;
    }
    if (!me.getId().equals(userId)) {
      throw new ForbiddenException("Access denied");
    }
  }
}
