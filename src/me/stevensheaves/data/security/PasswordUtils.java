package me.stevensheaves.data.security;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

class PasswordUtils {
    /**
     * Constructs a secure random number generator
     */
    private static final Random RANDOM = new SecureRandom();
    /**
     * The Charachters to be chosen from when creating salt.
     */
    private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    /**
     * Number of iterations to run hashing algorithm.
     */
    private static final int ITERATIONS = 10000;
    /**
     * Length of the resulting hashed password.
     */
    private static final int KEY_LENGTH = 256;

    /**
     * Method for generating salt (a string of random characters.).
     * @param length <code>int</code> value designating the length of the salt generated.
     * @return Returns the <code>Strint</code> value of the salt generated.
     */
    public static String getSalt(int length) {
        StringBuilder returnValue = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }
        return new String(returnValue);
    }

    /**
     * Generates and returns the hashed form of the password.
     * @param password The <code>char[]</code> value of the password to be hashed.
     * @param salt The salt value to be added to the
     * @return Returns the <code>byte[]</code> value of the newly hashed password.
     */
    public static byte[] hash(char[] password, byte[] salt) {
        PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH);
        Arrays.fill(password, Character.MIN_VALUE);
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            return skf.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new AssertionError("Error while hashing a password: " + e.getMessage(), e);
        } finally {
            spec.clearPassword();
        }
    }

    /**
     * Utility method for generating a hashed pasword.
     * @param password <code>String</code> string
     * @param salt The String to be used as salt
     * @return Returns the String form of the generated hashed password.
     */
    public static String generateSecurePassword(String password, String salt) {
        String returnValue = null;
        byte[] securePassword = hash(password.toCharArray(), salt.getBytes());

        returnValue = Base64.getEncoder().encodeToString(securePassword);

        return returnValue;
    }

    /**
     * Secure method for validating a provided password, agains its hashed form.
     * @param providedPassword The potentially-matching password provided by the user.
     * @param securedPassword The hashed (correct) password String
     * @param salt The salt which corresponds with the securedPassword
     * @return returns <code>true</code> if providedPassword is correct, and <code>false</code> otherwise.
     */
    public static boolean verifyUserPassword(String providedPassword,
                                             String securedPassword, String salt)
    {
        boolean returnValue = false;

        // Generate New secure password with the same salt
        String newSecurePassword = generateSecurePassword(providedPassword, salt);

        // Check if two passwords are equal
        returnValue = newSecurePassword.equalsIgnoreCase(securedPassword);

        return returnValue;
    }
}