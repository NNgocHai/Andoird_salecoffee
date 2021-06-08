package hcmute.edu.vn.mssv18110278.Security;

import java.security.MessageDigest;

public class PasswordHelpers {


  public static String passwordHash(String password) {
    MessageDigest messageDigest;
    try {
      messageDigest = MessageDigest.getInstance("SHA-256");
      messageDigest.update(password.getBytes("UTF-8"));
      byte[] digest = messageDigest.digest();
      return String.format("%064x", new java.math.BigInteger(1, digest));

    } catch (Exception e) {
      return null;
    }
  }

  public static boolean comparePassword(String dbPassword, String enteredPassword) {
    return dbPassword.equals(passwordHash(enteredPassword));
  }

}
