package me.stevensheaves.data.security;

public class ProtectUserPassword {

    public static String createSalt() {
        return PasswordUtils.getSalt(30);
    }
    public static String protectPassword(String password, String salt) {
        return PasswordUtils.generateSecurePassword(password, salt);
    }
}