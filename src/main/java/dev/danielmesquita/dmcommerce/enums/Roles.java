package dev.danielmesquita.dmcommerce.enums;

public enum Roles {
  ROLE_ADMIN("ROLE_ADMIN"),
  ROLE_CLIENT("ROLE_CLIENT");

  private final String authority;

  Roles(String authority) {
    this.authority = authority;
  }

  public String getAuthority() {
    return authority;
  }
}
