package am.ith.service.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ContextHolder {

  private static final ThreadLocal<String> userId = new InheritableThreadLocal<>();
  private static final ThreadLocal<String> jwtToken = new InheritableThreadLocal<>();

  public static String getUserId() {
    return userId.get();
  }

  public static void setUserId(String generatedId) {
    userId.set(generatedId);
  }

  public static String getToken() {
    return jwtToken.get();
  }

  public static void setToken(String token) {
    jwtToken.set(token);
  }

  public static void clear() {
    userId.remove();
    jwtToken.remove();
  }
}
