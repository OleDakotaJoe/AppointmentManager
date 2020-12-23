package me.stevensheaves.data.security;

public class VerifyProvidedPassword {

        public static boolean verifyPassword(String providedPassword, String securePassword, String salt) {
            return PasswordUtils.verifyUserPassword(providedPassword, securePassword, salt);
        }
}
