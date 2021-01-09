package me.stevensheaves.data.security;

/**
 * Wrapper class for secure access of the PasswordUtils class.
 */
public class ProtectUserPassword {

    /**
     * Generates a random string to be used as salt.
     * @return the String value of the generated salt.
     */
    public static String createSalt() {
        return PasswordUtils.getSalt(30);
    }

    /**
     * Generates a secure password from a provided password and salt.
     * @param password The password to be hashed
     * @param salt The salt which is added to the password to be hashed.
     * @return Returns the String value of the hashed and salted password.
     */
    public static String protectPassword(String password, String salt) {
        return PasswordUtils.generateSecurePassword(password, salt);
    }
}