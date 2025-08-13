package dev.danielmesquita.dmcommerce.projections;

public interface UserDetailsProjection {
  String getUsername();

  String getPassword();

  Long getRoleId();

  String getAuthority();
}
