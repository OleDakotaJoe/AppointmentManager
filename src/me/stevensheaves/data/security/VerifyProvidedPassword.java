package me.stevensheaves.data.security;

/**
 * Wrapper class for secure access of the PasswordUtils class.
 */
public class VerifyProvidedPassword {

    /**
     * Utility method for verifying whether a user-provided password is valid.
     *
     * @param providedPassword The user-provided password to be tested.
     * @param securePassword The hashed password to test the password against
     * @param salt The String value for the salt which corresponds to the securePassword
     * @return Returns true if the password was correct, and false otherwise.
     */
        public static boolean verifyPassword(String providedPassword, String securePassword, String salt) {
            return PasswordUtils.verifyUserPassword(providedPassword, securePassword, salt);
        }
}
