package am.ith.service.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResponseStatus {
  ACCOUNT_CREATED("Account created successfully"),
  EMAIL_EXISTS("Email is already registered"),
  USER_NAME_EXISTS("Username is already registered"),
  ACCOUNT_NOT_FOUND("Account not found"),
  CONTENT_NOT_FOUND("Content not found"),
  AUTHORIZATION_ERROR("You're not authorized to access this resource"),
  USER_ID_EXISTS("User Id exists");

  private final String message;
}
